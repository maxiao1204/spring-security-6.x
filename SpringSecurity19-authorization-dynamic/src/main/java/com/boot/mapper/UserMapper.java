package com.boot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.boot.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper//指定这是一个操作数据库的mapper
public interface UserMapper extends BaseMapper<User> {

    User findUserByUsername(String username);
}