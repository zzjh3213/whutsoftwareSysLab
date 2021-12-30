package whut.servlet.bookservlet;

import whut.interfaces.Delete;
import whut.service.BookService;
import whut.util.GetResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DeleteBookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookIdRef = req.getParameter("bookID");
        Delete deleteService = BookService.GetBookService();
        //boolean deleteBookSuc = (ServiceFactory.getBookService()).deleteBook(Integer.parseInt(bookIdRef));
        boolean deleteBookSuc = deleteService.deleteInfo(bookIdRef);

        PrintWriter out = GetResponse.getRespWriter(resp, "UTF-8", deleteBookSuc);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
