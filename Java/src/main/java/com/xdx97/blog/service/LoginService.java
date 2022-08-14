package com.xdx97.blog.service;

import com.xdx97.blog.bean.ResultObj;
import com.xdx97.blog.bean.dto.LoginDTO;

public interface LoginService {

    ResultObj loginIn(LoginDTO loginDTO);
}
