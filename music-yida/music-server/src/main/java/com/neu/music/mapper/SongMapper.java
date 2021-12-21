package com.neu.music.mapper;

import com.neu.music.entity.Song;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description: 歌曲
 * @Author: yida
 */
@Repository
public interface SongMapper {
    /**
     *增加
     */
    int insert(Song song);

    /**
     *修改
     */
    int update(Song song);

    /**
     * 删除
     */
    int delete(Integer id);

    /**
     * 根据主键查询整个对象
     */
    Song selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌曲
     */
    List<Song> allSong();

    /**
     * 根据歌名精确查询列表
     */
    List<Song> songOfName(String name);

    /**
     * 根据歌名模糊查询列表
     */
    List<Song> likeSongOfName(String name);

    /**
     * 根据歌手id查询
     */
    List<Song> songOfSingerId(Integer singerId);
}
















