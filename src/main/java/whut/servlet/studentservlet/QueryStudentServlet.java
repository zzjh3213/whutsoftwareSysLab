package whut.servlet.studentservlet;

import whut.bean.StudentBean;
import whut.factory.BeanFactory;
import whut.interfaces.Query;
import whut.service.StudentService;
import whut.util.GetResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * 查询学生信息，在管理员查询学生信息时会发送请求到这个servlet
 */
public class QueryStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> m = req.getParameterMap();
        PrintWriter out;
        Query<StudentBean> queryService = StudentService.GetStudentService();
        if (m.isEmpty()) {
            out = GetResponse.getRespWriter(resp, "UTF-8", queryService.showAllInfo());
        }
        else {
//            StudentBean studentBean = new StudentBean();
//            studentBean.setUserName(m.get("userName")[0]);
//            studentBean.setStudentMajor(m.get("studentMajor")[0]);
//            studentBean.setStudentName(m.get("studentName")[0]);
            StudentBean studentBean = BeanFactory.createStudentBean(req);
            out = GetResponse.getRespWriter(resp, "UTF-8", queryService.queryInfo(studentBean));
        }
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
