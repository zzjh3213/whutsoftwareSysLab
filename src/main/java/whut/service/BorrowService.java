package whut.service;

import whut.bean.BorrowBean;
import whut.bean.StudentBean;
import whut.factory.BeanFactory;
import whut.interfaces.*;
import whut.mapper.BookMapper;
import whut.mapper.BorrowMapper;
import whut.mapper.StudentMapper;
import whut.bean.BorrowRes;
import whut.util.GetSqlSession;
import org.apache.ibatis.session.SqlSession;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 负责处理借书还书的类
 */
public class BorrowService
        implements Query<BorrowRes>, Delete, Get<BorrowBean>, Borrow, Return {

    private static BorrowService borrowService;
    private BorrowService(){}
    public static BorrowService GetBorrowService() {
        if (borrowService == null) {
            borrowService = new BorrowService();
        }
        return  borrowService;
    }

    /**
     * 添加借书信息，参考{@link BorrowMapper#addBorrow(BorrowBean)}
     * @param borrowBean 待增加的借书记录信息
     * @return 增加借书记录成功或失败
     */
    public boolean addInfo(BorrowBean borrowBean) {
        SqlSession session = GetSqlSession.createSqlSession();
        assert session != null;
        BorrowMapper borrowMapper = session.getMapper(BorrowMapper.class);

        borrowMapper.addBorrow(borrowBean);
        session.commit();
        session.close();
        return true;
    }

    /**
     * 根据借书记录的Id获取对应的借书信息
     * @param borrowId 主码
     * @return 对应的借书信息或null
     */
    @Override
    public BorrowBean getInfo(Object borrowId) {
        SqlSession session = GetSqlSession.createSqlSession();
        assert session != null;
        BorrowMapper borrowMapper = session.getMapper(BorrowMapper.class);

        BorrowBean borrowBean = borrowMapper.getBorrowBean((Integer) borrowId);
        session.close();
        return borrowBean;
    }

    /**
     * 借书，增加借书记录到数据库中
     * @param borrowBean 借书信息
     * @return 本次借书返回的状态码
     */
    @Override
    public int borrow(BorrowBean borrowBean) {
        State stateService = StudentService.GetStudentService();
        String userName = borrowBean.getUserName();
        int id = borrowBean.getId();
        //判断能否借书
        //首先判断学生状态
        int state = stateService.getState(userName);
        if (state == StudentBean.TO_BE_DELETED) {
            return CANT_BORROW_FOR_BE_DELETED;
        } else if (state == StudentBean.IN_DEBT) {
            return CANT_BORROW_FOR_IN_DEBT;
        } else if (state == StudentBean.LIMIT) {
            return CANT_FOR_LIMIT;
        }
        //其次判断书本库存
        if (!BookService.GetBookService().bookRemain(id)) {
            return CANT_BORROW_FOR_NOT_ENOUGH;
        }

        //若学生能借书,建立数据库连接
        SqlSession session = GetSqlSession.createSqlSession();
        assert session != null;
        BorrowMapper borrowMapper = session.getMapper(BorrowMapper.class);
        //将学生的借书数加1
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);
        studentMapper.increaseBookCnt(userName);
        int curBookCnt = studentMapper.getBookCnt(userName);
        //将书籍数减1
        BookMapper bookMapper = session.getMapper(BookMapper.class);
        bookMapper.decreaseBookCnt(id);
        //将这条借书记录插入数据库
        borrowMapper.addBorrow(borrowBean);
        session.commit();
        session.close();
        //设置学生状态
        if (state == StudentBean.NO_BORROW) {
            stateService.setState(userName, StudentBean.BORROW);
        }
        if (curBookCnt == StudentBean.BOOK_CNT_LIMIT) {
            stateService.setState(userName, StudentBean.LIMIT);
        }

        return BORROW_SUCCESS;
    }

    /**
     * 还书
     * @param borrowId 借阅记录的主码
     * @param withDebt 表示此次还书是否需要计算欠款
     * @return 还书状态码和欠款
     */
    @Override
    public Map<String, String> returnBook(Integer borrowId, boolean withDebt) {
        SqlSession session = GetSqlSession.createSqlSession();
        assert session != null;

        //还书时，首先修改对应的借阅记录
        BorrowMapper borrowMapper = session.getMapper(BorrowMapper.class);
        double debt = 0;
        if (withDebt) {
            debt = DebtService.getDebtService().getDebt(borrowId);
        }
        Map<String, String> res = new HashMap<>();
        if (debt != 0) { //欠款不为0时
            res.put("state", String.valueOf(waitDebt));
            res.put("debt", String.valueOf(debt));
        } else { //欠款为0时
            boolean returnSuc = borrowMapper.returnBook(borrowId, LocalDate.now());
            int resState = returnFalse;
            if (returnSuc) { //存在对应借书记录时，修改其他表的记录
                BorrowBean borrowBean = borrowMapper.getBorrowBean(borrowId);
                int id = borrowBean.getId();
                String userName = borrowBean.getUserName();
                resState = returnSuccess;
                //将书籍库存加1
                BookMapper bookMapper = session.getMapper(BookMapper.class);
                bookMapper.increaseBookCnt(id);
                //将学生持有的书数量减1
                StudentMapper studentMapper = session.getMapper(StudentMapper.class);
                studentMapper.decreaseBookCnt(userName);
                int curBookCnt = studentMapper.getBookCnt(userName);
                //检查学生状态并在有必要时修改
                int state = studentMapper.getState(userName);
                if (curBookCnt != 0) { //持有书籍数量不为0
                    if (state == StudentBean.LIMIT) {
                        studentMapper.setStudentState(StudentBean.BORROW, userName);
                    } else if (state == StudentBean.IN_DEBT) {
                        studentMapper.setStudentState(StudentBean.BORROW, userName);
                    }
                } else { //持有书籍数量为0
                    if (state == StudentBean.TO_BE_DELETED) {
                        //若状态为待删除且不持有图书，将学生从数据库中删除
                        studentMapper.deleteStudent(userName);
                        resState = returnAndDelete;
                    } else {
                        //修改为未借书状态
                        studentMapper.setStudentState(StudentBean.NO_BORROW, userName);
                    }
                }
            }
            session.commit();
            session.close();
            res.put("state", String.valueOf(resState));
        }
        return res;
    }

    public static void main(String[] args) {
        Query<BorrowRes> query = BorrowService.GetBorrowService();
        System.out.println(query.showAllInfo());
    }

    /**
     * 获取数据库中的所有借书信息
     * @return 借书信息列表
     */
    @Override
    public List<BorrowRes> showAllInfo() {
        SqlSession session = GetSqlSession.createSqlSession();
        assert session != null;
        BorrowMapper borrowMapper = session.getMapper(BorrowMapper.class);

        BorrowRes borrowRes = BeanFactory.createBorrowRes(null);
        List<BorrowRes> res = borrowMapper.queryBorrowInfo(borrowRes);
        session.close();
        return res;
    }

    /**
     * 删除借书记录，如果图书未归还，那么这条借书记录将不能被删除
     * @param primaryKey 要删除信息的主码
     * @return 有匹配的记录则删除并返回true，否则返回false
     */
    @Override
    public Boolean deleteInfo(Object primaryKey) {
        SqlSession session = GetSqlSession.createSqlSession();
        assert session != null;
        BorrowMapper borrowMapper = session.getMapper(BorrowMapper.class);

        String borrowId = primaryKey.toString();
        boolean res = borrowMapper.deleteBorrowInfo(Integer.parseInt(borrowId));
        session.commit();
        session.close();
        return res;
    }

    /**
     * 根据传入的信息查找相应的借书记录
     * @param borrowRes 传入的信息，类型应为BorrowRes
     * @return 匹配的信息列表
     */
    @Override
    public List<BorrowRes> queryInfo(BorrowRes borrowRes) {
        SqlSession session = GetSqlSession.createSqlSession();
        assert session != null;
        BorrowMapper borrowMapper = session.getMapper(BorrowMapper.class);

        if (borrowRes.getType() != null &&
                borrowRes.getType().equals("未选择")) {
            borrowRes.setType(null);
        }
        List<BorrowRes> res = borrowMapper.queryBorrowInfo(borrowRes);
        session.close();
        return res;
    }



}
