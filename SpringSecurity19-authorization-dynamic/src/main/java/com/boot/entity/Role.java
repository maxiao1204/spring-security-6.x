package com.boot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@TableName("t_role")
public class Role implements GrantedAuthority {
    @TableId(value = "id",type = IdType.AUTO)
    private int id;
    @TableField("name")
    private String name;
    //权限编码
    @TableField("code")
    private String code;

    @TableField(exist = false)
    private String authority;

    @Override
    public String getAuthority() {
        return "ROLE_"+code;
    }
}
