package whut.mapper;


import org.apache.ibatis.annotations.Param;
import whut.bean.AdminBean;
import whut.bean.BookBean;

import java.util.List;

public interface BookAdminMapper {
    /**
     * 查询所有管理员信息
     * @return 所有管理员信息列表
     */
    List<AdminBean> showAllAdmin();

    /**
     * 向表中添加管理员
     * @param adminBean 用户信息
     * @return 添加成功失败
     */
    boolean addAdmin(AdminBean adminBean);

    /**
     * 查询数据库中是否存在用户名为 userName 的管理员
     * @param userName 用户名
     * @return 若存在，返回对应用户信息，否则返回null
     */
    AdminBean getAdmin(@Param("userName") String userName);

    /**
     * 通过用户名查询管理员
     * @param userName 管理员用户名
     * @return 匹配的管理员列表
     */
    List<AdminBean> queryAdmin(@Param("userName") String userName);

    /**
     * 通过用户名删图书管理员
     * @param userName 用户名
     * @return 若存在对应的图书管理员，则删除并返回true, 否则返回false
     */
    boolean deleteBookAdmin(@Param("userName") String userName);

    /**
     * 更新图书管理员信息
     * @param adminBean 待更新的图书管理员
     * @return 匹配的行数，通常为1。
     */
    boolean updateBook(AdminBean adminBean);
}
