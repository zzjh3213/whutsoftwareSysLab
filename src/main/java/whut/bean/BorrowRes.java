package whut.bean;

import java.time.LocalDate;

/**
 * 用于接收借书记录的查询结果的POJO
 */
public class BorrowRes {
    private Integer borrowId;
    private Integer Id;
    private String userName;
    private String Name;
    private String Author;
    private String Publisher;
    private String Type;        //书籍类别
    private LocalDate startDate; //借书时间
    private LocalDate expiryDate; //截至时间
    private LocalDate returnDate; //实际还书时间
    private Boolean isReturn;

    public BorrowRes() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        this.Id = id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getReturn() {
        return isReturn;
    }

    public void setReturn(Boolean aReturn) {
        isReturn = aReturn;
    }

    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        this.Author = author;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        this.Publisher = publisher;
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
        this.isReturn = returnDate != null;
    }

    @Override
    public String toString() {
        return "BorrowRes{" +
                "borrowId=" + borrowId +
                ", Id=" + Id +
                ", userName='" + userName + '\'' +
                ", Name='" + Name + '\'' +
                ", Author='" + Author + '\'' +
                ", Publisher='" + Publisher + '\'' +
                ", Type='" + Type + '\'' +
                ", startDate=" + startDate +
                ", expiryDate=" + expiryDate +
                ", returnDate=" + returnDate +
                ", isReturn=" + isReturn +
                '}' + '\n';
    }
}
