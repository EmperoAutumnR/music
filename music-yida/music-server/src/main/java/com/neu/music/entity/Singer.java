package com.neu.music.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 歌手
 * @Author: yida
 */
@Data
public class Singer implements Serializable {
    /*主键*/
    private Integer id;
    /*账号*/
    private String name;
    /*性别*/
    private Byte sex;
    /*头像*/
    private String pic;
    /*生日*/
    private Date birth;
    /*地区*/
    private String location;
    /*简介*/
    private String introduction;


}
