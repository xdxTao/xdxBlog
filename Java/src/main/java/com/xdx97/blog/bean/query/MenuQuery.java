package com.xdx97.blog.bean.query;

import com.xdx97.blog.common.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 菜单查询实体
 *
 * @author 小道仙97
 * @date 2022/03/20
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuQuery {

    /**
     * 菜单名称
     */
    private String name;


    /**
     * 启用状态
     */
    private StatusEnum status;
}
