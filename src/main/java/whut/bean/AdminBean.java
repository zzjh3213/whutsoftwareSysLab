package whut.bean;

import java.util.Objects;

public class AdminBean {
    private String userName;
    private String userPwd;

    public AdminBean() {
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

    @Override
    public String toString() {
        return "UserBean{" +
                "userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof AdminBean)) return false;
        AdminBean adminBean = (AdminBean) o;
        return Objects.equals(getUserName(), adminBean.getUserName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName());
    }

}
