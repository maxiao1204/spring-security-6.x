package com.boot.config;

import com.boot.auth.JwtAuthenticationTokenFilter;
import com.boot.security.LoginFailureHandler;
import com.boot.security.LoginSuccessHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

//@EnableWebSecurity:开启SpringSecurity 之后会默认注册大量的过滤器servlet filter
//过滤器链【责任链模式】SecurityFilterChain
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //authorizeHttpRequests:针对http请求进行授权配置
        //login登录接口需要匿名访问
        //permitAll:具有所有权限 也就可以匿名可以访问
        //anyRequest:任何请求 所有请求
        //authenticated:认证【登录】
        http.authorizeHttpRequests(authorizeHttpRequests->
                authorizeHttpRequests
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated()
        );

        //http:后面可以一直点 但是太多内容之后不美观
        //loginProcessingUrl:指定登录接口
        //successHandler:登录成功处理器
        //failureHandler:登录失败处理器
        //自定义登录接口
        http.formLogin(formLogin->
                formLogin
                        .loginProcessingUrl("/login").permitAll()
                        .successHandler(new LoginSuccessHandler())
                        .failureHandler(new LoginFailureHandler())

        );

        //在登录之前获取token并校验
        http.addFilterBefore(new JwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling(e->e.accessDeniedHandler(new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write("异常:"+accessDeniedException.getMessage());
            }
        }));

        //Customizer.withDefaults():关闭
        //http.csrf(Customizer.withDefaults());//跨域漏洞防御:关闭
        //http.csrf(e->e.disable());
        //http.csrf(crsf->crsf.disable());//相当于 http.csrf(Customizer.withDefaults());
        http.csrf(e->e.disable());//封装的太过于抽象比较难以阅读代码【装X】

        http.cors(e->e.disable());//跨域拦截关闭

        return http.build();
    }

}
