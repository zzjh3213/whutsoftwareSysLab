package whut.bean;

import java.time.LocalDate;

/**
 * 借书记录类，包含借书记录的ID，书名，借书时间，截止时间，实际还书时间
 */
public class BorrowBean {
    private Integer borrowId;
    private Integer Id;
    private String userName;
    private LocalDate startDate; //借书时间
    private LocalDate expiryDate; //截至时间
    private LocalDate returnDate; //实际还书时间

    public BorrowBean() {

    }

    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        this.Id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "BorrowBean{" +
                "borrowId=" + borrowId +
                ", Id=" + Id +
                ", userName='" + userName + '\'' +
                ", startDate=" + startDate +
                ", expiryDate=" + expiryDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
