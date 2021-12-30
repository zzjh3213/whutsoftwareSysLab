package whut.servlet.bookservlet;

import whut.bean.BookBean;
import whut.factory.BeanFactory;
import whut.interfaces.Add;
import whut.service.BookService;
import whut.util.GetResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AddBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Add<BookBean> addService = BookService.GetBookService();
        //BookBean bookToBeAdded = BookBean.createBookBeanByRequest(req);
        BookBean bookToBeAdded = BeanFactory.createBookBean(req);

//        boolean addSuc = (ServiceFactory.getBookService()).addBook(bookToBeAdded);
        boolean addSuc = addService.addInfo(bookToBeAdded);

        PrintWriter out = GetResponse.getRespWriter(resp, "UTF-8", addSuc);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
