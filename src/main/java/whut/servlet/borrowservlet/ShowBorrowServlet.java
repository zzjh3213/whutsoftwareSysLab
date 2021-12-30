package whut.servlet.borrowservlet;

import whut.factory.BeanFactory;
import whut.interfaces.Query;
import whut.bean.BorrowRes;
import whut.service.BorrowService;
import whut.util.GetResponse;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 显示某个用户的所有借阅信息
 */
public class ShowBorrowServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");

        Query<BorrowRes> queryService = BorrowService.GetBorrowService();
        BorrowRes borrowRes = BeanFactory.createBorrowRes(req);
        borrowRes.setUserName(userName);
        List<BorrowRes> borrowResList = queryService.queryInfo(borrowRes);

        PrintWriter out = GetResponse.getRespWriter(resp, "UTF-8", borrowResList);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
