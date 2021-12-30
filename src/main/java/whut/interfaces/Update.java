package whut.interfaces;

public interface Update<T> {
    /**
     * 更新信息，信息中应包含主码，根据这个主码来定位要更新的记录
     * @param bean 更新后的信息
     * @return 若有对应主码则更新并返回true，否则返回false
     */
    boolean updateInfo(T bean);
}
