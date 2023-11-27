package com.boot.provider;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

//在这里加一个标识用来记录验证码判断是否正确
public class MyWebAuthenticationDetails extends WebAuthenticationDetails {
    private boolean imageCodeIsRight;

    public boolean isImageCodeIsRight() {
        return imageCodeIsRight;
    }

    public MyWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        String requestCode = request.getParameter("captcha");
        HttpSession session = request.getSession();
        String sessionCode = (String) session.getAttribute("captcha");
        if(sessionCode!=null){
            session.removeAttribute("captcha");
            if(sessionCode.equals(requestCode)){
                this.imageCodeIsRight = true;
            }
        }
    }

    public MyWebAuthenticationDetails(String remoteAddress, String sessionId) {
        super(remoteAddress, sessionId);
    }
}
