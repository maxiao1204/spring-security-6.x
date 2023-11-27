package com.boot.provider;

import com.boot.exception.VerificationCodeException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyDaoAuthenticationProvider extends DaoAuthenticationProvider {

    public MyDaoAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder){
        super.setUserDetailsService(userDetailsService);
        super.setPasswordEncoder(passwordEncoder);
    }

    //在这里获取request 通过request来获取验证码 作比对

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        MyWebAuthenticationDetails details = (MyWebAuthenticationDetails) authentication.getDetails();

        if(!details.isImageCodeIsRight()){
            throw new VerificationCodeException();
        }

        super.additionalAuthenticationChecks(userDetails, authentication);
    }
}
