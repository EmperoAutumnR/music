package com.neu.music.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 评分
 * @Author: yida
 */
@Data
public class Rank implements Serializable {
    /*主键*/
    private Integer id;
    /*歌单id*/
    private Integer songListId;
    /*用户id*/
    private Integer consumerId;
    /*评分*/
    private Integer score;


}
