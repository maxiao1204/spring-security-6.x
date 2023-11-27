package com.boot.config;

import com.boot.auth.MyAuthorizationManager;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Resource
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Resource
    private MyAuthorizationManager authorizationManager;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeHttpRequests->
                authorizeHttpRequests
                        .requestMatchers("/login").permitAll()
                        .anyRequest().access(authorizationManager)

        );

        http.formLogin(formLogin->
                formLogin
                        .loginPage("/login").permitAll()
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/index")
                        .failureHandler(authenticationFailureHandler)
        );

        http.csrf(withDefaults());

        http.logout(logout->logout.invalidateHttpSession(true));

        return http.build();
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
