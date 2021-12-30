package whut.service;

import whut.bean.BookBean;
import whut.bean.ItemBean;
import whut.interfaces.*;
import whut.mapper.BookMapper;
import whut.util.GetSqlSession;
import whut.util.StringUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class BookService
        implements Update<BookBean>, Add<BookBean>, Delete, Query<BookBean> {

    private static BookService bookService;
    private BookService() {
    }

    public static BookService GetBookService() {
        if (bookService == null) {
            bookService = new BookService();
        }
        return bookService;
    }

    /**
     * 获取表中所有书本的信息
     * @return 所有书本信息的集合
     */
    @Override
    public List<BookBean> showAllInfo() {
        SqlSession session = GetSqlSession.createSqlSession();
        assert session != null;
        BookMapper bookMapper = session.getMapper(BookMapper.class);

        List<BookBean> res = bookMapper.showBooks();
        session.close();
        return res;
    }

    /**
     * 添加书籍，添加逻辑参考 {@link BookMapper#addBook(BookBean)}
     * @param bookBean 待添加书籍
     * @return 添加成功或失败
     */
    @Override
    public boolean addInfo(BookBean bookBean) {
        SqlSession session = GetSqlSession.createSqlSession();
        assert session != null;
        BookMapper bookMapper = session.getMapper(BookMapper.class);

        BookBean bookInDB = bookMapper.getBookBean(bookBean.getName(),
                bookBean.getAuthor(),bookBean.getPublisher());
        if (bookBean.equals(bookInDB)) { //若数据库中存在新添加的书，则添加失败
            return false;
        } else { //若数据库中不存在新添加的书，则将新书直接添加到数据库
            bookMapper.addBook(bookBean);
            session.commit();
            session.close();
            return true;
        }

    }

    /**
     * 删除书籍，且这本书籍一定会存在于数据库中(由前端保证)
     * @param bookId 待删除的书籍的ID
     * @return 删除成功或失败
     */
    @Override
    public Boolean deleteInfo(Object bookId) {
        SqlSession session = GetSqlSession.createSqlSession();
        assert session != null;
        BookMapper bookMapper = session.getMapper(BookMapper.class);

        boolean res = bookMapper.deleteBook(Integer.parseInt(bookId.toString()));
        session.commit();
        session.close();
        return res;
    }

    /**
     * 查询书籍，若书名，作者，书籍类型全为null，则返回一个空列表
     * @param itemBean 书籍参考信息
     * @return 查询书籍结果列表
     */
    @Override
    public List<BookBean> queryInfo(BookBean itemBean) {
        String bookName = itemBean.getName();
        String bookAuthor = itemBean.getAuthor();
        String bookType = itemBean.getType();
        //验证信息是否有效
        if (bookType != null && bookType.equals("未选择")) {
            bookType = null;
            itemBean.setType(null);
        }
        if (bookName == null && bookAuthor == null && bookType == null) {
            return null;
        } else {
            if (bookName != null) {
                itemBean.setName(StringUtil.removePreSpace(bookName));
            }
            if (bookAuthor != null) {
                itemBean.setAuthor(StringUtil.removePreSpace(bookAuthor));
            }
        }

        SqlSession session = GetSqlSession.createSqlSession();
        assert session != null;
        BookMapper bookMapper = session.getMapper(BookMapper.class);

        List<BookBean> res = bookMapper.queryBook(itemBean);
        session.close();
        return res;
    }

    /**
     * 判断对应书籍Id的书籍是否库存充足
     * @param bookId 书籍Id
     * @return 充足true或不充足false
     */
    public boolean bookRemain(int bookId) {
        SqlSession session = GetSqlSession.createSqlSession();
        assert session != null;
        BookMapper bookMapper = session.getMapper(BookMapper.class);

        boolean res = bookMapper.queryBookById(bookId).getRemain() > 0;
        session.close();
        return res;
    }

    /**
     *
     * 更新数据库中与bean的bookId相同的书籍的信息
     * @param bookBean 更新后的书籍信息
     * @return 若数据库中有匹配的记录，则更新并返回true，否则返回false
     */
    @Override
    public boolean updateInfo(BookBean bookBean) {
        SqlSession session = GetSqlSession.createSqlSession();
        assert session != null;
        BookMapper bookMapper = session.getMapper(BookMapper.class);

        boolean res = bookMapper.updateBook(bookBean);
        session.commit();
        session.close();
        return res;
    }

    public static void main(String[] args) {

    }

}
