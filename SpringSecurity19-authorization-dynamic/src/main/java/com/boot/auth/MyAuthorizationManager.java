package com.boot.auth;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

@Component
public class MyAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    @Override
    public void verify(Supplier<Authentication> authentication, RequestAuthorizationContext requestAuthorizationContext) {
        AuthorizationManager.super.verify(authentication, requestAuthorizationContext);
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext requestAuthorizationContext) {

        // 当前用户的权限信息 比如角色
        Collection<? extends GrantedAuthority> authorities = authentication.get().getAuthorities();
        // 当前请求上下文
        // 我们可以获取携带的参数
        Map<String, String> variables = requestAuthorizationContext.getVariables();
        // 我们可以获取原始request对象
        HttpServletRequest request = requestAuthorizationContext.getRequest();
        // 根据这些信息 和业务写逻辑即可 最终决定是否授权 isGranted
        boolean isGranted = true;
        return new AuthorizationDecision(isGranted);
    }
}
