package com.xdx97.blog.bean.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xdx97.blog.common.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 用户实体
 *
 * @author 小道仙97
 * @date 2022/03/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("xdx_user")
public class User {

    /**
     * id
     */
    @TableId
    private Integer id;

    /**
     * 角色名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;

    /**
     * 角色id
     */
    @NotNull(message = "角色不能为空")
    private Integer roleId;

    /**
     * 启用状态
     */
    private StatusEnum status;

    /**
     * 加盐
     */
    private String salt;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建时间
     */
    private LocalDateTime createAt;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 更新时间
     */
    private LocalDateTime updateAt;

    /**
     * 更新人
     */
    private Integer updateBy;
}
