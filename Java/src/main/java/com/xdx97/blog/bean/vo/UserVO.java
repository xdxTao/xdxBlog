package com.xdx97.blog.bean.vo;

import com.xdx97.blog.common.enums.StatusEnum;
import lombok.Data;

/**
 * userVO
 *
 * @author xdx97
 * @date 22/03/26
 */
@Data
public class UserVO {

    /**
     * id
     */
    private Integer id;

    /**
     * name
     */
    private String name;

    /**
     * roleId
     */
    private Integer roleId;

    /**
     * roleName
     */
    private String roleName;

    /**
     * 启用状态
     */
    private StatusEnum status;
}
