package whut.bean;

import java.util.Objects;

public class StudentBean {
    private String studentName;
    private String studentMajor;
    private Integer studentState;
    private String userName;
    private String userPwd;
    private Integer bookCnt; //学生持有书籍数量
    /**
     * 学生状态码
     */
    public final static int NO_BORROW = 0; //学生不持有图书
    public final static int BORROW = 1; //学生持有图书
    public final static int TO_BE_DELETED = 2; //学生待删除
    public final static int IN_DEBT = 3; //学生有超期未还书
    public final static int LIMIT = 4; //学生达到借书上限

    public final static int BOOK_CNT_LIMIT = 5; //学生借书上限

    public StudentBean() {
        super();
    }

    public Integer getBookCnt() {
        return bookCnt;
    }

    public void setBookCnt(Integer bookCnt) {
        this.bookCnt = bookCnt;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentMajor() {
        return studentMajor;
    }

    public void setStudentMajor(String studentMajor) {
        this.studentMajor = studentMajor;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public Integer getStudentState() {
        return studentState;
    }

    public void setStudentState(Integer studentState) {
        this.studentState = studentState;
    }

    @Override
    public String toString() {
        return "StudentBean{" +
                "studentName='" + studentName + '\'' +
                ", studentMajor='" + studentMajor + '\'' +
                ", studentState=" + studentState +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentBean that = (StudentBean) o;
        return userName.equals(that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }
}
