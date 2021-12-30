package whut.interfaces;

import whut.mapper.BookMapper;

public interface Delete {
    /**
     * 删除数据库中的信息
     * @param primaryKey 要删除信息的主码
     * @return 数据库中有对应主码，则删除成功，否则删除失败
     */
    Boolean deleteInfo(Object primaryKey);
}
