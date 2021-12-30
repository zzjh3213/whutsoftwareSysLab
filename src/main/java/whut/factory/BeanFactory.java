package whut.factory;

import whut.bean.BookBean;
import whut.bean.BorrowBean;
import whut.bean.BorrowRes;
import whut.bean.StudentBean;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

public class BeanFactory {
    /**
     * 根据Http请求发送的信息来创建书籍实体类
     * @param req Http请求
     * @return 创建后的书籍实体类
     */
    public static BookBean createBookBean(HttpServletRequest req) {
        BookBean bookBean = new BookBean();
        if (req != null) {
            if (req.getParameter("bookId") != null) {
                bookBean.setId(Integer.parseInt(req.getParameter("bookId")));
            } else {
                bookBean.setId(null);
            }
            bookBean.setAuthor(req.getParameter("bookAuthor"));
            bookBean.setName(req.getParameter("bookName"));
            if (req.getParameter("bookPrice") != null) {
                bookBean.setPrice(Double.parseDouble(req.getParameter("bookPrice")));
            } else {
                bookBean.setPrice(null);
            }
            if (req.getParameter("bookTotalCnt") != null) {
                bookBean.setRemain(Integer.parseInt(req.getParameter("bookTotalCnt")));
            } else {
                bookBean.setRemain(null);
            }
            bookBean.setType(req.getParameter("bookType"));
            if (req.getParameter("bookTotalCnt") != null) {
                bookBean.setTotalCnt(Integer.parseInt(req.getParameter("bookTotalCnt")));
            } else {
                bookBean.setTotalCnt(null);
            }
            bookBean.setPublisher(req.getParameter("bookPublisher"));
            bookBean.setImg(req.getParameter("dataurl"));
            bookBean.setIntroduce(req.getParameter("bookIntroduce"));
        }
        return bookBean;
    }

    /**
     * 根据Http请求发送的信息来创建学生实体类
     * @param req Http请求
     * @return 创建后的学生实体类
     */
    public static StudentBean createStudentBean(HttpServletRequest req) {
        StudentBean studentBean = new StudentBean();
        if (req != null) {
            studentBean.setStudentMajor(req.getParameter("studentMajor"));
            studentBean.setStudentName(req.getParameter("studentName"));
            studentBean.setUserName(req.getParameter("userName"));
            studentBean.setUserPwd(req.getParameter("userPwd"));
        }
        return studentBean;
    }

    /**
     * 根据Http请求发送的信息来创建借阅信息实体类
     * @param req Http请求
     * @return 创建后的借阅信息实体类
     */
    public static BorrowBean createBorrowBean(HttpServletRequest req, int timeLimit) {
        BorrowBean borrowBean = new BorrowBean();
        if (req != null) {
            borrowBean.setId(Integer.parseInt(req.getParameter("bookId")));
            borrowBean.setUserName(req.getParameter("userName"));
            borrowBean.setStartDate(LocalDate.now());
            borrowBean.setExpiryDate(LocalDate.now().plusDays(timeLimit));
        }
        return borrowBean;
    }

    /**
     * 根据Http请求发送的信息来创建详细借阅信息实体类
     * @param req Http请求
     * @return 创建后的详细借阅信息实体类
     */
    public static BorrowRes createBorrowRes(HttpServletRequest req) {
        BorrowRes borrowRes = new BorrowRes();
        if (req != null) {
            borrowRes.setUserName(req.getParameter("userName"));
            borrowRes.setName(req.getParameter("bookName"));
            borrowRes.setType(req.getParameter("bookType"));
            if (req.getParameter("returnType") != null &&
                    !req.getParameter("returnType").equals("全部")) {
                borrowRes.setReturn(req.getParameter("returnType").equals("已还"));
            }
        }
        return borrowRes;
    }
}
