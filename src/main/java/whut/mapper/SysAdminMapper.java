package whut.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import whut.bean.AdminBean;

public interface SysAdminMapper {
    /**
     * 获取用户名为userName的系统管理员
     * @param userName 用户名
     * @return 对应的系统管理员或null
     */
    @Select("select * from sysadmin where userName = #{userName}")
    AdminBean getSysAdmin(@Param("userName") String userName);
}
