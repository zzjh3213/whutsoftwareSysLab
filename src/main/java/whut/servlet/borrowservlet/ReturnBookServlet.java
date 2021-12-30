package whut.servlet.borrowservlet;

import whut.interfaces.Return;
import whut.service.BookService;
import whut.service.BorrowService;
import whut.util.GetResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * 学生还书，主要涉及状态的修改，包括借阅记录的状态修改和学生状态修改
 */
public class ReturnBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer borrowId = Integer.parseInt(req.getParameter("borrowId"));
        boolean debt = Boolean.parseBoolean(req.getParameter("debtState"));

        Return returnService = BorrowService.GetBorrowService();

        PrintWriter out = GetResponse.getRespWriter(resp, "UTF-8",
                returnService.returnBook(borrowId, debt));
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
