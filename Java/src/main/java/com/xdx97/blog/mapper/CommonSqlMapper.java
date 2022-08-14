package com.xdx97.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommonSqlMapper {

    /**
     * 获取上级id
     * @param table
     * @param id
     * @return
     */
    Integer parentId(@Param("table") String table,@Param("id") Integer id);
}
