package com.boot.auth;


import cn.hutool.json.JSONUtil;
import com.boot.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.BufferedReader;
import java.io.IOException;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private  final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/login",
            "POST");


    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {


        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        User user = obtainUser(request);

        UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(user.getUsername(),user.getPassword());

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private User obtainUser(HttpServletRequest request) throws IOException {
        //前端传递:{username:zs,password:123456}
        //把servlet好好的学习一下!!!
        BufferedReader reader = request.getReader();//从请求体获取数据
        StringBuffer sbf = new StringBuffer();
        String line = null;
        while ((line = reader.readLine())!=null){
            sbf.append(line);
        }

        User user = JSONUtil.parseObj(sbf.toString()).toBean(User.class);
        return user;
    }
}
