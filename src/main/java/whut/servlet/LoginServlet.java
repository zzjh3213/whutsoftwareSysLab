package whut.servlet;

import whut.interfaces.Login;
import whut.factory.LoginFactory;
import whut.service.*;
import whut.util.GetResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取客户端发送的消息
        String userName = req.getParameter("username");
        String userPwd = req.getParameter("password");
        int ident = Integer.parseInt(req.getParameter("ident"));
        HttpSession session = req.getSession();

        Login login;
        login = LoginFactory.getLoginService(ident);
        //判断登录是否成功
        boolean loginSuccess = login.login(userName, userPwd);
        //登录成功则记录必要信息
        if (loginSuccess) {
            session.setAttribute("userName", userName);
            session.setAttribute("userPwd", userPwd);
            session.setAttribute("changeUser", false);
            session.setAttribute("ident", ident);
            if (ident == Login.STU) {
                //登录成功后检查学生是否有超时未还的书
                StudentService studentService = (StudentService) login;
                studentService.checkExpiry(userName);
                session.setAttribute("studentState", ((StudentService) login).getState(userName));
            }
        }

        PrintWriter out = GetResponse.getRespWriter(resp, "UTF-8", loginSuccess);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
