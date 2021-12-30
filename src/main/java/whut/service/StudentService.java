package whut.service;

import whut.bean.StudentBean;
import whut.interfaces.*;
import whut.mapper.BorrowMapper;
import whut.mapper.StudentMapper;
import whut.util.GetSqlSession;
import whut.util.StringUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class StudentService
        implements Update<StudentBean>, Login, Add<StudentBean>,
        Delete, Query<StudentBean>, Contains, Get<StudentBean>, State {

    private static StudentService studentService;
    private StudentService(){
        super();
    }
    public static StudentService GetStudentService() {
        if (studentService == null) {
            studentService = new StudentService();
        }
        return studentService;
    }

    public static final int DELETEFALSE = 0;
    public static final int DELETEDELAY = 1;

    /**
     * 注册学生，并把信息插入数据库
     * @param studentBean 学生注册输入的信息
     * @return 在有重复的用户名的时添加失败并返回false，否则返回true
     */
    @Override
    public boolean addInfo(StudentBean studentBean) {
        SqlSession session = GetSqlSession.createSqlSession();
        assert session != null;
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);

        if (studentBean == null) {
            return false;
        }

        if (studentMapper.getStudent(studentBean.getUserName()) != null){
            return false;
        }
        else {
            boolean res = studentMapper.addStudent(studentBean);
            session.commit();
            session.close();
            return res;
        }

    }

    /**
     * 显示所有学生信息
     * @return 学生信息列表
     */
    @Override
    public List<StudentBean> showAllInfo() {
        SqlSession session = GetSqlSession.createSqlSession();
        assert session != null;
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);

        List<StudentBean> res = studentMapper.showAllStudents();
        session.close();
        return res;
    }

    /**
     * 查询所有匹配学生信息，传入的bean中若有值为null的属性，那么该属性将不会参与匹配
     * @param studentBean 用于查询的学生信息
     * @return 所有匹配的学生记录
     */
    @Override
    public List<StudentBean> queryInfo(StudentBean studentBean) {
        String studentName = studentBean.getStudentName();
        String userName = studentBean.getUserName();

        if (studentBean.getStudentMajor() != null && studentBean.getStudentMajor().equals("未选择")) {
            studentBean.setStudentMajor(null);
        }

        if (studentBean.getStudentMajor() == null &&
                studentName == null && userName == null) {
            return null;
        } else {
            if (studentName != null) {
                studentBean.setStudentName(StringUtil.removePreSpace(studentName));
            }
            if (userName != null) {
                studentBean.setUserName(StringUtil.removePreSpace(userName));
            }
        }

        SqlSession session = GetSqlSession.createSqlSession();
        assert session != null;
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);

        List<StudentBean> res = studentMapper.queryStudent(studentBean);
        session.close();
        return res;
    }

    /**
     * 更新数据库中用户名与bean的用户名匹配的记录
     * @param studentBean 更新后的信息
     * @return 若存在匹配的记录，则更新并返回true，否则返回false
     */
    @Override
    public boolean updateInfo(StudentBean studentBean) {
        SqlSession session = GetSqlSession.createSqlSession();
        assert session != null;
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);

        boolean res = studentMapper.updateStudent(studentBean);
        session.commit();
        session.close();
        return res;
    }

    /**
     * 删除学生，若学生未持有图书，则直接删除，并且会删除该学生的所有借阅记录返回成功；
     * 若学生有未归还的图书，则直接将学生状态改为待删除，返回失败
     * @param userNameRef 删除学生的用户名
     * @return 删除成功或失败
     */
    @Override
    public Boolean deleteInfo(Object userNameRef) {
        String userName = (String) userNameRef;
        Integer studentState = this.getState(userName);
        if (studentState == null) {
            return null;
        }
        SqlSession session = GetSqlSession.createSqlSession();
        assert session != null;
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);

        if (studentState != StudentBean.NO_BORROW) { //若学生持有图书，则将学生状态改为待删除
            studentMapper.setStudentState(StudentBean.TO_BE_DELETED, userName);
            session.commit();
            session.close();
            return false;
        } else { //若学生未持有图书，则直接在数据库中删除学生
            studentMapper.deleteStudent(userName);
            session.commit();
            session.close();
            return true;
        }
    }

    /**
     * 设置学生状态
     * @param newState 新状态
     * @param userName 学生用户名
     */
    @Override
    public boolean setState(Object userName, Integer newState) {
        SqlSession session = GetSqlSession.createSqlSession();
        assert session != null;
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);

        boolean res = studentMapper.setStudentState(newState, (String) userName);
        session.commit();
        session.close();
        return res;
    }

    /**
     * 根据userName来获取对应学生的状态
     * @param userName 用户名
     * @return 状态
     */
    @Override
    public Integer getState(Object userName) {
        SqlSession session = GetSqlSession.createSqlSession();
        assert session != null;
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);

        Integer res = studentMapper.getState((String) userName);
        session.close();
        return res;
    }

    /**
     * 检查用户名为userName的学生是否有书籍到期未还，若有则将学生状态设置为到期未还
     * @param userName 用户名
     * @return 有对应的学生则检查状态并返回true，否则返回false
     */
    public boolean checkExpiry(String userName) {
        State stateService = StudentService.GetStudentService();
        Integer curState = stateService.getState(userName);
        if (curState == null) {
            return false;
        }
        if (curState.equals(StudentBean.IN_DEBT) || curState.equals(StudentBean.TO_BE_DELETED)) {
            return true;
        }
        SqlSession session = GetSqlSession.createSqlSession();
        assert session != null;
        BorrowMapper borrowMapper = session.getMapper(BorrowMapper.class);

        if (borrowMapper.getExpiryBorrowBean(userName) != null) {
            stateService.setState(userName, StudentBean.IN_DEBT);
        }
        return true;
    }

    public static void main(String[] args) {
        SqlSession session = GetSqlSession.createSqlSession();
        assert session != null;
        BorrowMapper borrowMapper = session.getMapper(BorrowMapper.class);

        System.out.println(borrowMapper.getExpiryBorrowBean("s1mple"));
    }

    /**
     * 修改用户名为userName的学生的密码
     * @param userName 用户名
     * @param newUserPwd 新密码
     * @return 若数据库中有匹配的用户名，则修改密码并返回true，否则返回false
     */
    public boolean changeStudentPwd(String userName, String newUserPwd) {
        StudentBean studentBean = new StudentBean();
        studentBean.setUserName(userName);
        studentBean.setUserPwd(newUserPwd);

        return this.updateInfo(studentBean);
    }

    @Override
    public boolean login(String userName, String userPwd) {
        if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(userPwd)) {
            return false;
        }
        SqlSession session = GetSqlSession.createSqlSession();
        assert session != null;
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);

        StudentBean stuInDB = studentMapper.getStudent(userName);
        session.close();
        return stuInDB != null && userPwd.equals(stuInDB.getUserPwd());
    }



    /**
     * 判断学生表中是否存在该用户名
     * @param userName 用户名
     * @return 存在返回true，否则返回false
     */
    @Override
    public boolean contains(Object userName) {
        SqlSession session = GetSqlSession.createSqlSession();
        assert session != null;
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);

        boolean res = studentMapper.getStudent((String) userName) != null;
        session.close();
        return res;
    }

    /**
     * 根据用户名获取一个特定的学生
     * @param userName 用户名
     * @return 存在返回对应学生，否则返回null
     */
    @Override
    public StudentBean getInfo(Object userName) {
        SqlSession session = GetSqlSession.createSqlSession();
        assert session != null;
        StudentMapper studentMapper = session.getMapper(StudentMapper.class);

        StudentBean res = studentMapper.getStudent((String) userName);
        session.close();
        return res;
    }
}
