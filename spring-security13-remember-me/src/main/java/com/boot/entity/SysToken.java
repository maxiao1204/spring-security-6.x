package com.boot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName("t_sys_token")
@Data
public class SysToken {
    @TableField(value = "username")
    private  String username;

    @TableField(value = "series")
    private  String series;

    @TableField(value = "token")
    private  String tokenValue;

    @TableField(value = "last_used")
    private  Date date;

}
