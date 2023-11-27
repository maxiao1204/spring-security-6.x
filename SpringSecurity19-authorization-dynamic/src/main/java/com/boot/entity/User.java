package com.boot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@TableName("t_user")
@Data
public class User implements UserDetails {
    @TableId(value = "id",type = IdType.AUTO)
    private int id;

    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;

    @Getter(AccessLevel.NONE)
    @TableField(exist = false)
    private boolean accountNonExpired=true;

    @Getter(AccessLevel.NONE)
    @TableField(exist = false)
    private boolean accountNonLocked=true;

    @Getter(AccessLevel.NONE)
    @TableField(exist = false)
    private boolean credentialsNonExpired=true;

    @Getter(AccessLevel.NONE)
    @TableField(value = "enabled")
    private boolean enabled;

    @TableField(exist = false)
    private List<Role> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
