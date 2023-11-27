package com.boot.controller;


import com.boot.service.FilterService;
import com.boot.vo.UserEntity;
import jdk.jfr.DataAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class IndexController {

    //@Secured({"ROLE_USERxxxx"})
    //@PreAuthorize("hasRole('USERxxx')")
    //@PreAuthorize("#age > 30")
    //@PreAuthorize("@ph.check('USERx')")
    //@PreAuthorize("principal.username.equals('zs')")
    @GetMapping("/index")
    public String index(Integer age){
        return "index";
    }


    @ResponseBody
    //filterObject:是返回的集合的一条数据
    //@PostFilter("filterObject.username == authentication.principal.username")
    //@PostFilter("filterObject.id%2==0")
    @PostFilter("hasRole('admin') and filterObject.username == authentication.principal.username")
    @GetMapping("/users")
    public List<UserEntity> getAllUser() {
        //注意这里配合postFilter过滤 如果直接使用Arrays的数组 则会抛出异常:java.lang.UnsupportedOperationException
        List<UserEntity> userList = new ArrayList<>(Arrays.asList(
                new UserEntity(1L,"admin","123456"),
                new UserEntity(2L,"test","123456"),
                new UserEntity(3L,"王五","123456"),
                new UserEntity(4L,"赵六","123456"),
                new UserEntity(5L,"小王","123456"),
                new UserEntity(6L,"小张","123456")
        ));
        return userList;
    }

    @Autowired
    FilterService filterService;

    @ResponseBody
    @GetMapping("/users2")
    public List<Integer> getUserInfos() {
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ids.add(i);
        }

        List<UserEntity> userList = new ArrayList<>(Arrays.asList(
                new UserEntity(1L,"admin","123456"),
                new UserEntity(2L,"test","123456"),
                new UserEntity(3L,"王五","123456"),
                new UserEntity(4L,"赵六","123456"),
                new UserEntity(5L,"小王","123456"),
                new UserEntity(6L,"小张","123456")
        ));

        return filterService.doFilter(ids, userList);
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
