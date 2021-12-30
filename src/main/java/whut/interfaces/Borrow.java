package whut.interfaces;

import whut.bean.BorrowBean;

public interface Borrow {
    /**
     * 借书结果的状态码
     */
    int CANT_BORROW_FOR_BE_DELETED = 0;
    int CANT_BORROW_FOR_NOT_ENOUGH = 1;
    int BORROW_SUCCESS = 2;
    int CANT_BORROW_FOR_IN_DEBT = 3;
    int CANT_FOR_LIMIT = 4;

    /**
     * 借书期限
     */
    int DELIMITATIONS = 30;

    /**
     * 借书
     * @param borrowBean 借阅记录
     * @return 借阅状态码
     */
    int borrow(BorrowBean borrowBean);
}
