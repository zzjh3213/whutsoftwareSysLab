package whut.interfaces;

public interface Contains {
    /**
     * 判断数据库中是否存在该主码
     * @param primaryKey 主码
     * @return 存在则返回true，否则返回false
     */
    boolean contains(Object primaryKey);
}
