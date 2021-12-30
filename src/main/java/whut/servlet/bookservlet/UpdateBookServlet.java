package whut.servlet.bookservlet;

import whut.bean.BookBean;
import whut.factory.BeanFactory;
import whut.interfaces.Update;
import whut.service.BookService;
import whut.util.GetResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdateBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        BookBean bookBean = BookBean.createBookBeanByRequest(req);
        BookBean bookBean = BeanFactory.createBookBean(req);
        Update<BookBean> updateService = BookService.GetBookService();

        PrintWriter out = GetResponse.getRespWriter(resp, "UTF-8",
                updateService.updateInfo(bookBean));
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


}
