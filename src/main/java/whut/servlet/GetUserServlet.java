package whut.servlet;

import whut.util.GetResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 在进入主页时，获取当前登录用户的信息。<br/>
 * 若一个浏览器登录了多个用户，最新登录的用户会覆盖之前的用户,此时若之前用户与最新用户不是同一个用户，那么之前的用户在刷新浏览器时会被强制退出登录。
 * 但之前的用户的信息仍然会记录在客户端，这意味着之前用户不点击刷新按钮，那么他就仍然能继续使用系统。<br/>
 * 需要注意的是有两个客户端动作会向这个servlet发送请求，一个是从登录界面点击登录且登录成功后进入主页，这时请求中的参数“userName”将为null，
 * 另一个是在主页点击刷新，这时请求中的参数“userName”将为一个具体的值。本类将以此来判断请求的来源。
 */
public class GetUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //首先从session中获取本次会话的信息
        HttpSession session = req.getSession();
        String userName = (String) session.getAttribute("userName");
        String userPwd = (String) session.getAttribute("userPwd");
        Integer studentState = (Integer) session.getAttribute("studentState");
        //获取从客户端传过来的用户名
        String curUserName = req.getParameter("userName");
        //判断客户端传过来的信息是否与session中保存的信息一致
        boolean changeUser = false;
        boolean tmp = (boolean) session.getAttribute("changeUser");
        //若请求的来源是刷新并且session中保存的用户名与客户端传来的用户名不同，则说明有新的用户登录，此时将session的“changeUser”改为true
        if (curUserName != null && !(curUserName.equals(userName))) {
            changeUser = true;
            session.setAttribute("changeUser", true);
        }
        //若请求的来源是登录并且session中保存的"changeUser"属性为true(这意味着在这之前有新的用户登录)，则将“changeUser”改为false(这是因为每次登录新用户都覆盖之前的用户)
        if (curUserName == null && tmp) {
            changeUser = true;
            session.setAttribute("changeUser", false);
        }

        Map<String, String> m = new HashMap<>();
        m.put("userName", userName);
        m.put("userPwd", userPwd);
        m.put("changeUser", String.valueOf(changeUser));
        if (studentState != null) {
            m.put("studentState", String.valueOf(studentState));
        }
        PrintWriter out = GetResponse.getRespWriter(resp, "UTF-8", m);
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
