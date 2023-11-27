package com.boot.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.StringUtils;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

//@EnableWebSecurity:开启SpringSecurity 之后会默认注册大量的过滤器servlet filter
//过滤器链【责任链模式】SecurityFilterChain
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //authorizeHttpRequests:针对http请求进行授权配置
        //login登录页面需要匿名访问
        //permitAll:具有所有权限 也就可以匿名可以访问
        //anyRequest:任何请求 所有请求
        //authenticated:认证【登录】
        http.authorizeHttpRequests(authorizeHttpRequests->
                authorizeHttpRequests
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/users").permitAll()
                        .anyRequest().authenticated()
        );
        //现在我们借助异常处理配置一个未授权页面:【实际上是不合理的 我们应该捕获异常信息 通过异常类型来判断是什么异常】
        http.exceptionHandling(e->e.accessDeniedPage("/noAuth"));

        //http:后面可以一直点 但是太多内容之后不美观
        //loginPage:登录页面
        //loginProcessingUrl:登录接口 过滤器
        //defaultSuccessUrl:登录成功之后访问的页面
        http.formLogin(formLogin->
                formLogin
                        .loginPage("/login").permitAll()
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/index")

        );

        //Customizer.withDefaults():关闭
        //http.csrf(Customizer.withDefaults());//跨域漏洞防御:关闭
        //http.csrf(e->e.disable());
        //http.csrf(crsf->crsf.disable());//相当于 http.csrf(Customizer.withDefaults());
        http.csrf(withDefaults());//封装的太过于抽象比较难以阅读代码【装X】

        //退出
        http.logout(logout->logout.invalidateHttpSession(true));

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        //admin用户具有admin、user角色
        //UserDetails user1 = User.withUsername("admin").password("123456").roles("admin","user").build();
        //UserDetails user2 = User.withUsername("user").password("123456").roles("user").build();

        UserDetails user1 = User.withUsername("admin").password("123456").roles("USER").build();
        UserDetails user2 = User.withUsername("user").password("123456").roles("user").build();

        return new InMemoryUserDetailsManager(user1,user2);
    }

    /**
     * PasswordEncoder:加密编码
     * 实际开发中开发环境一般是明文加密 在生产环境中是密文加密 也就可以可以配置多种加密方式
     *
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        //明文加密
        return NoOpPasswordEncoder.getInstance();
    }

}
