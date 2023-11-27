package com.boot.handler;

import org.springframework.stereotype.Component;

@Component("ph")
public class PermHandler {

    public boolean check(String role){
        return role.equals("USER");
    }

}
