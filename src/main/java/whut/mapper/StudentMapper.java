package whut.mapper;

import whut.bean.StudentBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {

    /**
     * 注册学生，将注册的学生的信息插入数据库
     * 来判断数据库中是否有相同的学生信息
     * @param stuInfo 注册的学生的信息
     * @return 数据库中存在相同用户名的学生，则添加失败并返回false，否则返回true
     */
    boolean addStudent(StudentBean stuInfo);

    /**
     * 查询数据库中是否存在用户名为 userName 的学生
     * @param userName 学生用户名
     * @return 若存在，返回对应学生信息，否则返回null
     */
    StudentBean getStudent(@Param("userName") String userName);

    /**
     * 显示所有学生信息
     * @return 学生信息列表
     */
    List<StudentBean> showAllStudents();

    /**
     * 根据学生信息查询学生
     * @param stuInfo 学生信息
     * @return 匹配的学生列表
     */
    List<StudentBean> queryStudent(StudentBean stuInfo);

    /**
     * 将用户名与stuRef相同的学生信息修改为stuRef的信息(本系统不允许修改学生用户名)
     * @param stuRef 更新后的信息
     * @return 若数据库中存在匹配的用户名，则更新并返回true，否则返回false
     */
    boolean updateStudent(StudentBean stuRef);

    /**
     * 删除对应用户名的学生信息
     * @param userName 待删除学生的用户名
     * @return 删除的条目数
     */
    boolean deleteStudent(@Param("userName") String userName);

    /**
     * 获取对应用户名的学生状态
     * @param userName 用户名
     * @return 学生状态
     */
    Integer getState(@Param("userName") String userName);

    /**
     * 设置学生状态
     * @param newState 新状态
     * @return 修改状态的学生数
     */
    boolean setStudentState(@Param("studentState") int newState, @Param("userName") String userName);

    /**
     * 根据用户名将学生的借书数加一
     * @param userName 用户名
     * @return 若存在该学生，则加一并返回true，否则返回false
     */
    boolean increaseBookCnt(@Param("userName")String userName);

    /**
     * 根据用户名将学生的借书数减一
     * @param userName 用户名
     * @return 若存在该学生，则减一并返回true，否则返回false
     */
    boolean decreaseBookCnt(@Param("userName")String userName);

    /**
     * 根据用户名获取学生的借书数
     * @param userName 用户名
     * @return 若存在该学生，则返回借书数，否则返回null
     */
    Integer getBookCnt(@Param("userName")String userName);

}
