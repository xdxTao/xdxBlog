package com.xdx97.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xdx97.blog.bean.ResultObj;
import com.xdx97.blog.bean.entity.User;
import com.xdx97.blog.bean.query.UserQuery;

/**
 * 用户
 */
public interface UserService extends IService<User> {

    /**
     * 当前用户的信息
     * @return
     */
    ResultObj information(Integer id);

    /**
     * 添加用户
     * @param user
     * @return
     */
    ResultObj add(User user);

    /**
     * 用户列表
     * @param userQuery
     * @return
     */
    ResultObj lists(UserQuery userQuery);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    ResultObj modify(User user);




}
