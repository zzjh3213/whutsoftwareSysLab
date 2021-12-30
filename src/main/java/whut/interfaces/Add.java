package whut.interfaces;

public interface Add<T> {
    /**
     * 向数据库中添加信息
     * @param bean 信息
     * @return 添加成功或失败
     */
    boolean addInfo(T bean);
}
