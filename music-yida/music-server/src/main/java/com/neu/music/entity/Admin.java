package com.neu.music.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 管理员
 * @Author: yida
 */
@Data
public class Admin implements Serializable {
    /*主键*/
    private Integer id;
    /*账号*/
    private String name;
    /*密码*/
    private String password;
}
