package whut.servlet.adminservlet;

import whut.bean.AdminBean;
import whut.interfaces.Update;
import whut.service.SysAdminService;
import whut.util.GetResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/updateAdmin")
public class UpdateBookAdminervlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AdminBean adminBean = new AdminBean();
        adminBean.setUserName(req.getParameter("userName"));
        adminBean.setUserPwd(req.getParameter("userPwd"));
        Update<AdminBean> updateService = SysAdminService.GetSysAdminService();

        PrintWriter out = GetResponse.getRespWriter(resp, "UTF-8", updateService.updateInfo(adminBean));
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
