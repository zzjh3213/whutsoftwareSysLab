package whut.servlet.borrowservlet;

import whut.bean.BorrowBean;
import whut.factory.BeanFactory;
import whut.interfaces.Borrow;
import whut.service.BorrowService;
import whut.util.GetResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static whut.interfaces.Borrow.DELIMITATIONS;

/**
 * 学生借书
 */
public class BorrowBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String bookId = req.getParameter("bookId");
//        String userName = req.getParameter("userName");
//        //创建借书记录
//        BorrowBean borrowBean = new BorrowBean();
//        borrowBean.setId(Integer.parseInt(bookId));
//        borrowBean.setUserName(userName);
//        borrowBean.setStartDate(LocalDate.now());
//        borrowBean.setExpiryDate(LocalDate.now().plusDays(30));
        BorrowBean borrowBean = BeanFactory.createBorrowBean(req, DELIMITATIONS);
        Borrow borrow = BorrowService.GetBorrowService();

        PrintWriter out = GetResponse.getRespWriter(resp, "UTF-8",
                borrow.borrow(borrowBean));
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
