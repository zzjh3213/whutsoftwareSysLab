package whut.mapper;

import whut.bean.BorrowBean;
import whut.bean.BorrowRes;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

public interface BorrowMapper {
    /**
     * 增加借书记录
     * @param borrowInfo 待增加的借书记录信息
     * @return 添加数量
     */
    boolean addBorrow(BorrowBean borrowInfo);


    /**
     * 查询所有匹配的借书信息,如果传入的参考信息全为null，则返回全部借书信息
     * @param borrowRes 借书信息(包含书本id以及书名，用户名，书籍类型的一项或多项)
     * @return 借书信息列表
     */
    List<BorrowRes> queryBorrowInfo(BorrowRes borrowRes);

    /**
     * 将borrowId对应的借阅记录改为已还
     * @param borrowId 借书记录主码
     * @param returnDate 还书日期
     * @return 若对应的记录是未还，则改为已还并返回true，否则返回false
     */
    boolean returnBook(@Param("borrowId") Integer borrowId, @Param("returnDate") LocalDate returnDate);

    /**
     * 删除借书记录，若图书未还，则该记录不能删除
     * @param borrowId 要删除记录的主码
     * @return 有对应的主码则删除并返回true，否则返回false
     */
    boolean deleteBorrowInfo(@Param("borrowId") Integer borrowId);

    /**
     * 根据borrowId获取对应借书信息
     * @param borrowId 借书信息的主码
     * @return 对应借书信息，没有对应的借书信息则返回null
     */
    BorrowBean getBorrowBean(@Param("borrowId") Integer borrowId);

    /**
     * 获取对应用户名的学生的超时未还的借书记录，若没有超时未还则返回null
     * @param userName 用户名
     * @return 超时未还的记录或null
     */
    BorrowBean getExpiryBorrowBean(@Param("userName") String userName);
}
