package whut.interfaces;

import java.util.List;

public interface Query<T> {
    /**
     * 显示数据库中对应表的所有信息
     * @return 信息列表
     */
    List<T> showAllInfo();

    /**
     * 根据信息查询对应表中的对应的记录
     * @return 对应记录列表
     */
    List<T> queryInfo(T bean);
}
