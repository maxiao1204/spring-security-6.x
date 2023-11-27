package com.boot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("t_menu")
@Data
public class Menu {
    //菜单id
    @TableId(value = "id",type = IdType.AUTO)
    private int id;
    //菜单名称
    @TableField(value = "name")
    private String name;
    //菜单URL
    @TableField(value = "url")
    private String url;
    //上级菜单id
    @TableField("parent_id")
    private int parentId;
}
