package com.neu.music.mapper;

import com.neu.music.entity.SongList;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description: 歌单
 * @Author: yida
 */
@Repository
public interface SongListMapper {
    /**
     *增加
     */
    int insert(SongList songList);

    /**
     *修改
     */
    int update(SongList songList);

    /**
     * 删除
     */
    int delete(Integer id);

    /**
     * 根据主键查询整个对象
     */
    SongList selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌单
     */
    List<SongList> allSongList();

    /**
     * 根据标题精确查询歌单列表
     */
    List<SongList> songListOfTitle(String title);

    /**
     * 根据标题模糊查询歌单列表
     */
    List<SongList> likeTitle(String title);

    /**
     * 根据风格模糊查询歌单列表
     */
    List<SongList> likeStyle(String style);


}
















