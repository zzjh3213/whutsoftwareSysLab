package whut.interfaces;

import java.util.Map;

public interface Return {
    /**
     * 还书状态码
     */
    int returnSuccess = 0;
    int returnFalse = 1;
    int returnAndDelete = 2;
    int waitDebt = 3;

    /**
     * 还书
     * @param borrowId 还书时对应借阅记录的Id
     * @param withDebt 表示还书时是否计算欠款,true代表本次还书计算欠款，false代表本次还书不计算欠款
     * @return 还书的状态码以及欠款，Map的Key应为“state”和“debt”
     */
    Map<String, String> returnBook(Integer borrowId, boolean withDebt);
}
