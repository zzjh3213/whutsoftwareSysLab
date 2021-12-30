package whut.servlet.adminservlet;

import whut.bean.AdminBean;
import whut.interfaces.Query;
import whut.service.SysAdminService;
import whut.util.GetResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/queryAdmin")
public class QueryBookAdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        AdminBean adminBean = new AdminBean();
        adminBean.setUserName(userName);
        Query<AdminBean> query = SysAdminService.GetSysAdminService();
        List<AdminBean> adminBeanList;
        if (userName != null) {
            adminBeanList = query.queryInfo(adminBean);
        } else {
            adminBeanList = query.showAllInfo();
        }
        PrintWriter out = GetResponse.getRespWriter(resp, "UTF-8", adminBeanList);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
