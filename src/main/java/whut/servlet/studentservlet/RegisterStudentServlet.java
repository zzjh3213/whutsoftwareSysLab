package whut.servlet.studentservlet;

import whut.bean.StudentBean;
import whut.factory.BeanFactory;
import whut.interfaces.Add;
import whut.interfaces.Contains;
import whut.service.StudentService;
import whut.util.GetResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 注册学生，在学生注册账号时会发送请求到这个servlet
 */
public class RegisterStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out;
        //判断是否存在相同用户名
        String userName = req.getParameter("userName");
        Contains containsService = StudentService.GetStudentService();
        if (containsService.contains(userName)) {
            out = GetResponse.getRespWriter(resp, "UTF-8", false);
            out.close();
            return;
        }
        //通过用户名检测后，设置注册学生信息
        String userPwd = req.getParameter("userPwd");
        String studentName = req.getParameter("studentName");
        String studentMajor = req.getParameter("studentMajor");
        if (userPwd != null && studentName != null && studentMajor != null) { //当输入密码，学生姓名，专业后进行数据库插入
            //设置学生信息
//            StudentBean studentBean = new StudentBean();
//            studentBean.setUserName(userName);
//            studentBean.setStudentName(studentName);
//            studentBean.setUserPwd(userPwd);
//            studentBean.setStudentMajor(studentMajor);

            StudentBean studentBean = BeanFactory.createStudentBean(req);
            //在点击注册按钮时，再次检查是否有相同用户名
            if (containsService.contains(studentBean.getUserName())) {
                //若存在相同用户名，则注册失败
                out = GetResponse.getRespWriter(resp, "UTF-8", false);
                out.close();
            } else {
                //若不存在相同用户名，则注册成功并将信息插入数据库
                Add<StudentBean> addService = StudentService.GetStudentService();
                addService.addInfo(studentBean);
                out = GetResponse.getRespWriter(resp, "UTF-8", true);
                out.close();
            }
        } else {  //输入信息不完整时不进行注册
            out = GetResponse.getRespWriter(resp, "UTF-8",true);
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
