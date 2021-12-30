package whut.service;

import org.apache.ibatis.session.SqlSession;
import whut.bean.BorrowBean;
import whut.mapper.BorrowMapper;
import whut.util.GetSqlSession;

import java.time.LocalDate;

/**
 * 计算欠款的类
 */
public class DebtService {
    private static DebtService debtService;
    private DebtService(){}
    public static DebtService getDebtService() {
        if (debtService == null) {
            debtService = new DebtService();
        }
        return debtService;
    }

    /**
     * 计算borrowId对应借阅记录的欠款
     * @param borrowId 借阅记录的主码
     * @return 欠款
     */
    public double getDebt(int borrowId) {
        SqlSession session = GetSqlSession.createSqlSession();
        assert session != null;
        BorrowMapper borrowMapper = session.getMapper(BorrowMapper.class);

        BorrowBean borrowBean = borrowMapper.getBorrowBean(borrowId);
        if (borrowBean.getReturnDate() != null) {
            return 0;
        }
        double res = 0.1 * (LocalDate.now().toEpochDay() - borrowBean.getExpiryDate().toEpochDay());
        return Math.max(res, 0);
    }

    public static void main(String[] args) {
        double debt = DebtService.getDebtService().getDebt(436);
        if (debt != 0)
            System.out.println("debt");
        else
            System.out.println("nodebt");
    }

}
