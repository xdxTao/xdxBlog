package com.xdx97.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdx97.blog.bean.ResultObj;
import com.xdx97.blog.bean.dto.LoginDTO;
import com.xdx97.blog.bean.entity.User;
import com.xdx97.blog.bean.vo.InformationVO;
import com.xdx97.blog.common.constant.SystemConstant;
import com.xdx97.blog.common.enums.StatusCodeEnum;
import com.xdx97.blog.mapper.UserMapper;
import com.xdx97.blog.service.LoginService;
import com.xdx97.blog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class LoginServiceImpl extends ServiceImpl<UserMapper, User> implements LoginService {

    @Autowired
    private UserService userService;

    @Override
    public ResultObj loginIn(LoginDTO loginDTO) {

        User user = this.lambdaQuery()
                .eq(User::getName, loginDTO.getUsername())
                .one();

        if (Objects.isNull(user)) {
            return ResultObj.fail(StatusCodeEnum.LOGIN_PARMAS_FAIL);
        }

        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String encryptStr = md5.digestHex(loginDTO.getPassword() + user.getSalt());

        if (!encryptStr.equals(user.getPassword())) {
            return ResultObj.fail(StatusCodeEnum.LOGIN_PARMAS_FAIL);
        }

        Map<String, Object> map = new HashMap<>(2);
        long expireDate = LocalDateTimeUtil.now().plusDays(5).toInstant(ZoneOffset.of("+8")).toEpochMilli();
        InformationVO informationVO = (InformationVO)userService.information(user.getId()).getData();
        informationVO.setId(user.getId());
        map.put("expireDate", expireDate);
        map.put("data", informationVO);

        String token = JWTUtil.createToken(map, SystemConstant.APP_KEY.getBytes());
        return ResultObj.success(token);
    }

    public static void main(String[] args) {
        String s = UUID.randomUUID().toString();
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        String s1 = md5.digestHex("123" + s);

        System.out.println(s);
        System.out.println(s1);

    }




}
