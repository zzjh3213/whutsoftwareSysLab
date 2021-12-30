package whut.servlet.bookservlet;

import whut.bean.BookBean;
import whut.bean.ItemBean;
import whut.factory.BeanFactory;
import whut.interfaces.Query;
import whut.service.BookService;
import whut.util.GetResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class QueryBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> m = req.getParameterMap();

        Query<BookBean> queryService = BookService.GetBookService();
        //BookBean bookBean = BookBean.createBookBeanByRequest(req);
        BookBean bookBean = BeanFactory.createBookBean(req);
        List<BookBean> bookList = queryService.queryInfo(bookBean);

        PrintWriter out = GetResponse.getRespWriter(resp, "UTF-8", bookList);
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
