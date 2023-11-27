package com.boot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.entity.User;
import com.boot.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends IService<User>, UserDetailsService {

}
