package com.boot.controller;

import com.boot.entity.User;
import com.boot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/user/save")
    public String userSave(String username,String password){
        String encodingId = "MD5";
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(encodingId, new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("MD5"));

        PasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(encodingId, encoders);
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        user.setEnabled(true);

        userMapper.insert(user);

        return "redirect:/login";
    }

}
