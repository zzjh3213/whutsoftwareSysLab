package whut.servlet.studentservlet;

import whut.bean.StudentBean;
import whut.interfaces.Get;
import whut.service.StudentService;
import whut.util.GetResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 获取一个特定学生的信息，即根据用户名查询学生
 */
public class GetStudentInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        Get<StudentBean> getService = StudentService.GetStudentService();

        PrintWriter out = GetResponse.getRespWriter(resp, "UTF-8", getService.getInfo(userName));
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
