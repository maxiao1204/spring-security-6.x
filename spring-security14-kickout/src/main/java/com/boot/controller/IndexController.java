package com.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    SessionRegistry sessionRegistry;

    @GetMapping("/index")
    public String index(Model model){

        //所有已经登录的用户
        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();

        model.addAttribute("users",allPrincipals);

        return "index";
    }

    @GetMapping("/kickout")
    public String kickout(String username){
        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();

        for (Object allPrincipal : allPrincipals) {
            //获取该用户所有已经登录的session会话:未失效的session
            List<SessionInformation> allSessions = sessionRegistry.getAllSessions(allPrincipal, false);

            User user = (User) allPrincipal;

            if(user.getUsername().equals(username)){
                allSessions.forEach(e->e.expireNow());//将所有已经登录的session会话都给失效
            }

        }


        return "redirect:/index";
    }

    @GetMapping("/admin/api")
    public String admin(){
        return "admin";
    }

    @ResponseBody
    @GetMapping("/admin/api/a")
    public String adminA(){
        return "adminA";
    }

    @ResponseBody
    @GetMapping("/admin/api/a/b/c/d")
    public String adminABCD(){
        return "adminABCD";
    }

    @GetMapping("/user/api")
    public String user(){
        return "user";
    }

    @ResponseBody
    @GetMapping("/user/api/my/center")
    public String user_center(){
        return "用户中心";
    }



    @GetMapping("/app/api")
    public String app(){
        return "app";
    }


    @GetMapping("/noAuth")
    public String noAuth(){
        return "noAuth";
    }


}
