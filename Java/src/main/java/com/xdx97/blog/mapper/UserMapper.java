package com.xdx97.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xdx97.blog.bean.entity.User;
import com.xdx97.blog.bean.query.UserQuery;
import com.xdx97.blog.bean.vo.InformationVO;
import com.xdx97.blog.bean.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    IPage<UserVO> lists(@Param("query") UserQuery userQuery);

    InformationVO getInformationById(@Param("id") int id);
}
