package whut.servlet.borrowservlet;

import whut.factory.BeanFactory;
import whut.interfaces.Query;
import whut.bean.BorrowRes;
import whut.service.BorrowService;
import whut.util.GetResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 管理员查询学生借书记录
 */
public class GetRecordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BorrowRes> borrowResList;
        PrintWriter out;
        Query<BorrowRes> queryService = BorrowService.GetBorrowService();
        if (req.getParameterMap().isEmpty()) {
            borrowResList = queryService.showAllInfo();
        } else {
            BorrowRes borrowRes = BeanFactory.createBorrowRes(req);
//            borrowRes.setUserName(req.getParameter("userName"));
//            borrowRes.setName(req.getParameter("bookName"));
//            borrowRes.setType(req.getParameter("bookType"));
//            if (!req.getParameter("returnType").equals("全部")) {
//                borrowRes.setReturn(req.getParameter("returnType").equals("已还"));
//            }
            borrowResList = queryService.queryInfo(borrowRes);
        }
        out = GetResponse.getRespWriter(resp, "UTF-8", borrowResList);
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
