package com.xdx97.blog.bean.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xdx97.blog.common.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * 菜单实体
 *
 * @author 小道仙97
 * @date 2022/03/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("xdx_menu")
public class Menu {

    /**
     * id
     */
    @TableId
    private Integer id;

    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
    private String name;

    /**
     * 父id
     */
    private Integer parentId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * icon
     */
    private String icon;

    /**
     * 菜单url
     */
    private String menuUrl;

    /**
     * 启用状态
     */
    private StatusEnum status;

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
