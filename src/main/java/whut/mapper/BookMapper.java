package whut.mapper;

import whut.bean.BookBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {
    /**
     * 查询数据库中的所有书籍
     * @return 包含所有书籍的书籍列表
     */
    List<BookBean> showBooks();

    /**
     * 添加书籍至数据库中，若待添加的书籍已存在于数据库中,则添加失败
     * @param bookToBeAdded 待添加的书籍
     * @return 添加成功或失败
     */
    boolean addBook(BookBean bookToBeAdded);

    /**
     * 查询数据库中是否存在某一本书
     * @param bookName 书名
     * @param bookAuthor 作者
     * @param bookPublisher 出版社
     * @return 存在返回对应书籍信息，否则返回null
     */
    BookBean getBookBean(@Param("Name") String bookName, @Param("Author") String bookAuthor, @Param("Publisher") String bookPublisher);

    /**
     * 删除书籍
     * @param bookId 待删除书籍的ID
     * @return 删除书籍数量
     */
    boolean deleteBook(Integer bookId);

    /**
     * 更新书籍信息
     * @param bookToBeUpdate 待更新的书籍
     * @return 更新成功返回true
     */
    boolean updateBook(BookBean bookToBeUpdate);

    /**
     * 根据书籍参考信息查询书籍
     * @param bookRef 书籍参考信息，包含书籍的名字，作者，类型三者中的一项或多项
     * @return 所有匹配的书籍
     */
    List<BookBean> queryBook(BookBean bookRef);

    /**
     * 根据书籍的Id查找书籍
     * @param bookId 书籍Id
     * @return 对应的书籍信息或者null
     */
    BookBean queryBookById(@Param("Id") int bookId);

    /**
     * 将对应的书籍库存加1
     * @param bookId 书籍id
     * @return 存在该书籍则加1并返回true，否则返回false
     */
    boolean increaseBookCnt(@Param("Id") int bookId);

    /**
     * 将对应的书籍库存减1
     * @param bookId 书籍id
     * @return 存在该书籍则减1并返回true，否则返回false
     */
    boolean decreaseBookCnt(@Param("Id") int bookId);
}
