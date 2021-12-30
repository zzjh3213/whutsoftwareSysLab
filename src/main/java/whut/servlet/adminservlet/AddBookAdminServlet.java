package whut.servlet.adminservlet;

import whut.bean.AdminBean;
import whut.interfaces.Add;
import whut.service.BookAdminService;
import whut.util.GetResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 添加管理员
 */
public class AddBookAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("adminName");
        String userPwd = req.getParameter("adminPwd");
        AdminBean adminBean = new AdminBean();
        adminBean.setUserName(userName);
        adminBean.setUserPwd(userPwd);
        Add<AdminBean> addService = BookAdminService.GetAdminService();

        PrintWriter out = GetResponse.getRespWriter(resp, "UTF-8",
                addService.addInfo(adminBean));
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
