package com.xdx97.blog.common.utils;

import com.xdx97.blog.mapper.CommonSqlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 一些可以通用的SQL放在这里
 */
@Service
public class CommonSqlUtils {

    @Autowired
    private CommonSqlMapper commonSqlMapper;


    public List<Integer> allParentId(String table, Integer id) {
        List<Integer> list = new ArrayList<>();
        allParentId(table, id, list);
        return list;
    }

    private void allParentId(String table, Integer id, List<Integer> list) {
        if (id == null || id == 0) {
            return ;
        }
        list.add(0, id);
        int parentId = commonSqlMapper.parentId(table, id);
        allParentId(table, parentId, list);
    }

}
