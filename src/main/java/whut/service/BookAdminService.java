package whut.service;

import whut.bean.AdminBean;
import whut.interfaces.*;
import whut.mapper.BookAdminMapper;
import whut.util.GetSqlSession;
import whut.util.StringUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class BookAdminService
        implements Login, Add<AdminBean> {

    private static BookAdminService bookAdminService;
    protected BookAdminService(){}
    public static BookAdminService GetAdminService() {
        if (bookAdminService == null) {
            bookAdminService = new BookAdminService();
        }
        return bookAdminService;
    }

    @Override
    public boolean login(String userName, String userPwd) {
        if(StringUtil.isEmpty(userName) || StringUtil.isEmpty(userPwd)) {
            return false;
        }
        SqlSession session = GetSqlSession.createSqlSession();
        assert session != null;
        BookAdminMapper bookAdminMapper = session.getMapper(BookAdminMapper.class);

        AdminBean userRes = bookAdminMapper.getAdmin(userName);
        session.close();
        //判断密码是否正确
        return userRes != null && userPwd.equals(userRes.getUserPwd());
    }

    /**
     * 向表中添加的管理员,若存在相同用户名的管理员则添加失败
     * @param adminBean 用户信息
     * @return 添加成功返回true，否则返回失败
     */
    @Override
    public boolean addInfo(AdminBean adminBean) {
        if (adminBean == null) {
            return false;
        }

        SqlSession session = GetSqlSession.createSqlSession();
        assert session != null;
        BookAdminMapper bookAdminMapper = session.getMapper(BookAdminMapper.class);

        //确保数据库中不存在相同用户名的管理员
        if (bookAdminMapper.getAdmin(adminBean.getUserName()) != null) {
            return false;
        } else {
            boolean res = bookAdminMapper.addAdmin(adminBean);
            session.commit();
            session.close();
            return res;
        }
    }
}
