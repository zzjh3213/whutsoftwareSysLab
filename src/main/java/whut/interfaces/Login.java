package whut.interfaces;

public interface Login {
    int STU = 0;
    int BOOK_ADMIN = 1;
    int SYS_ADMIN = 2;
    /**
     * 根据用户名来判断用户名和密码是否匹配
     * @param userName 用户名
     * @return 匹配返回true，否则返回false
     */
    boolean login(String userName, String userPwd);
}
