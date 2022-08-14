package com.xdx97.blog.controller;

import com.xdx97.blog.bean.ResultObj;
import com.xdx97.blog.bean.dto.LoginDTO;
import com.xdx97.blog.common.annotation.SkipLoginAuth;
import com.xdx97.blog.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/in")
    @SkipLoginAuth
    public ResultObj loginIn(@RequestBody @Valid LoginDTO loginDTO) {
        return loginService.loginIn(loginDTO);
    }

    /**
     * 目前登录是基于JWT做的，是每天状态的，没办法给它失效，现在的退出，只是前端把token清除
     *
     * 但在实际生产中的确存在想要让用户token失效的场景，这里预留一个接口
     *
     * 你可以拿到当前用户的token后，把它存入一个内存map中，然后这里面的数据就相当于过期就好了，（不过需要考虑时间限制，不然这个map会越来越大）
     *
     * @param token
     * @return
     */
    @PostMapping("/out")
    @SkipLoginAuth
    public ResultObj loginOut(@RequestHeader("XDX-TOKEN") String token) {

        System.out.println(token);
        return ResultObj.success();
    }



}
