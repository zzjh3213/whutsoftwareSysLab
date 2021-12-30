package whut.servlet.adminservlet;

import whut.interfaces.Delete;
import whut.service.SysAdminService;
import whut.util.GetResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/deleteAdmin")
public class DeleteSysAdmin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Delete deleteService = SysAdminService.GetSysAdminService();

        PrintWriter out = GetResponse.getRespWriter(resp, "UTF-8",
                deleteService.deleteInfo(req.getParameter("userName")));
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
