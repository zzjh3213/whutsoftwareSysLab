package whut.service;

import org.apache.ibatis.session.SqlSession;
import whut.bean.AdminBean;
import whut.interfaces.*;
import whut.mapper.BookAdminMapper;
import whut.mapper.SysAdminMapper;
import whut.util.GetSqlSession;
import whut.util.StringUtil;

import java.util.List;

public class SysAdminService
        implements Update<AdminBean>, Login, Query<AdminBean>, Delete {

    private static SysAdminService sysAdminService;
    private SysAdminService() {}
    public static SysAdminService GetSysAdminService() {
        if (sysAdminService == null) {
            sysAdminService = new SysAdminService();
        }
        return sysAdminService;
    }

    /**
     * 系统管理员登录
     * @param userName 用户名
     * @param userPwd 密码
     * @return 登录成功或失败
     */
    @Override
    public boolean login(String userName, String userPwd) {
        SqlSession session = GetSqlSession.createSqlSession();
        assert session != null;
        SysAdminMapper sysAdminMapper = session.getMapper(SysAdminMapper.class);

        AdminBean sysAdminInDB = sysAdminMapper.getSysAdmin(userName);
        session.close();
        //判断密码是否正确
        return sysAdminInDB != null && userPwd.equals(sysAdminInDB.getUserPwd());
    }

    /**
     * 删除数据库中用户名为userName的图书管理员
     * @param userName 要删除信息的主码
     * @return 有对应的主码则删除并返回true，否则返回false
     */
    @Override
    public Boolean deleteInfo(Object userName) {
        SqlSession session = GetSqlSession.createSqlSession();
        assert session != null;
        BookAdminMapper bookAdminMapper = session.getMapper(BookAdminMapper.class);

        boolean res = bookAdminMapper.deleteBookAdmin((String) userName);
        session.commit();
        session.close();
        return res;
    }

    @Override
    public List<AdminBean> showAllInfo() {
        SqlSession session = GetSqlSession.createSqlSession();
        assert session != null;
        BookAdminMapper bookAdminMapper = session.getMapper(BookAdminMapper.class);

        List<AdminBean> res = bookAdminMapper.showAllAdmin();
        session.close();
        return res;
    }

    @Override
    public List<AdminBean> queryInfo(AdminBean bean) {
        SqlSession session = GetSqlSession.createSqlSession();
        assert session != null;
        BookAdminMapper bookAdminMapper = session.getMapper(BookAdminMapper.class);

        List<AdminBean> res = bookAdminMapper.queryAdmin(bean.getUserName());
        session.close();
        return res;
    }

    /**
     * 更新图书管理员的信息
     * @param adminBean 更新后的信息
     * @return 有对应的图书管理员则更新并返回true，否则返回false
     */
    @Override
    public boolean updateInfo(AdminBean adminBean) {
        SqlSession session = GetSqlSession.createSqlSession();
        assert session != null;
        BookAdminMapper bookAdminMapper = session.getMapper(BookAdminMapper.class);

        boolean res = bookAdminMapper.updateBook(adminBean);
        session.commit();
        session.close();
        return res;
    }
}
