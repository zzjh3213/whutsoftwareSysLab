package whut.interfaces;

/**
 * 该接口主要负责通过用户名来获取和设置用户的状态
 */
public interface State {
    /**
     * 根据主码获取状态
     * @param primaryKey 主码
     * @return 状态码
     */
    Integer getState(Object primaryKey);

    /**
     * 将主码对应记录设置为新状态
     * @param primaryKey 主码
     * @param newState 新状态
     * @return 存在对应主码则返回true，否则返回false
     */
    boolean setState(Object primaryKey, Integer newState);
}
