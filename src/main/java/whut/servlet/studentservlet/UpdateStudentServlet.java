package whut.servlet.studentservlet;

import whut.bean.StudentBean;
import whut.factory.BeanFactory;
import whut.interfaces.Update;
import whut.service.StudentService;
import whut.util.GetResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 更新学生信息，通常管理员更新学生信息时会将请求发送到这个servlet
 */
public class UpdateStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Update<StudentBean> updateService = StudentService.GetStudentService();

//        StudentBean studentBean = new StudentBean();
//        studentBean.setUserName(req.getParameter("userName"));
//        studentBean.setStudentMajor(req.getParameter("studentMajor"));
//        studentBean.setStudentName(req.getParameter("studentName"));
//        studentBean.setUserPwd(req.getParameter("userPwd"));

        StudentBean studentBean = BeanFactory.createStudentBean(req);
        boolean updateSuc = updateService.updateInfo(studentBean);

        PrintWriter out = GetResponse.getRespWriter(resp, "UTF-8", updateSuc);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
