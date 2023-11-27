package com.boot.service;

import com.boot.entity.Role;
import com.boot.entity.User;
import com.boot.mapper.RoleMapper;
import com.boot.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findUserByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("用户名未找到!");
        }

        List<Role> roles = roleMapper.findRolesByUserId(user.getId());
        user.setAuthorities(roles);

        return user;
    }

}
