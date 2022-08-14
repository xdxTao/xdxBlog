package com.xdx97.blog.controller;

import cn.hutool.core.lang.Assert;
import com.xdx97.blog.bean.ResultObj;
import com.xdx97.blog.bean.entity.User;
import com.xdx97.blog.bean.query.UserQuery;
import com.xdx97.blog.bean.vo.InformationVO;
import com.xdx97.blog.common.enums.StatusCodeEnum;
import com.xdx97.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户
 *
 * @author 小道仙97
 * @date 2022-03-26
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 当前用户的信息
     * @return
     */
    @GetMapping("information")
    public ResultObj information(InformationVO informationVO) {
//        return userService.information(informationVO.getId());
        return userService.information(1);
    }

    @PostMapping("add")
    public ResultObj add(@Valid @RequestBody User user) {
        return userService.add(user);
    }

    /**
     * 用户列表
     * @param userQuery
     * @return
     */
    @PostMapping("list")
    public ResultObj lists(@Valid @RequestBody UserQuery userQuery) {
        return userService.lists(userQuery);
    }

    /**
     * 用户更新
     * @return
     */
    @PostMapping("modify")
    public ResultObj modify(@RequestBody User user) {
        Assert.isFalse(user==null || user.getId() == null, StatusCodeEnum.MISS_REQUIRED_PARAMS.getName()+":ID");
        return userService.modify(user);
    }


}
