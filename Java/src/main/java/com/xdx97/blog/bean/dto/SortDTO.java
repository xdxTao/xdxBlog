package com.xdx97.blog.bean.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 排序DTO
 *
 * @author 小道仙97
 * @date 2022-03-19
 */
@Data
@Accessors(chain = true)
public class SortDTO {

    @NotNull(message = "参数id不能为null")
    private Integer id;

    @NotNull(message = "参数sort不能为null")
    private Integer sort;
}
