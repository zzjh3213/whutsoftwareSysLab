package whut.interfaces;

public interface Get<T> {
    /**
     * 根据主码获取数据库中某一特定信息
     * @param primaryKey 主码
     * @return 主码对应的信息
     */
    T getInfo(Object primaryKey);
}
