package whut.servlet.studentservlet;

import whut.interfaces.Delete;
import whut.service.StudentService;
import whut.util.GetResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 管理员删除学生
 */
public class DeleteStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Delete deleteService = StudentService.GetStudentService();

        PrintWriter out = GetResponse.getRespWriter(resp, "UTF-8",
                deleteService.deleteInfo(req.getParameter("userName")));
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
