package com.neu.music.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 歌单包含歌曲
 * @Author: yida
 */
@Data
public class ListSong implements Serializable {
    /*主键*/
    private Integer id;
    /*歌曲id*/
    private Integer songId;
    /*歌单id*/
    private Integer songListId;


}
