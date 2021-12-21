package com.neu.music.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 收藏
 * @Author: yida
 */
@Data
public class Collect implements Serializable {
    /*主键*/
    private Integer id;
    /*用户id*/
    private Integer userId;
    /*收藏类型（0歌曲1歌单）*/
    private Byte type;
    /*歌曲id*/
    private Integer songId;
    /*歌单id*/
    private Integer songListId;
    /*收藏时间*/
    private Date createTime;


}
