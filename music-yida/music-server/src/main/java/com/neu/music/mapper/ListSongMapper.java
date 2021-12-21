package com.neu.music.mapper;

import com.neu.music.entity.ListSong;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description: 歌单包含歌曲
 * @Author: yida
 */
@Repository
public interface ListSongMapper {
    /**
     *增加
     */
    int insert(ListSong listSong);

    /**
     *修改
     */
    int update(ListSong listSong);

    /**
     * 删除
     */
    int delete(Integer id);

    /**
     * 根据歌曲id和歌单id删除
     */
    int deleteBySongIdAndSongListId(Integer songId,Integer songListId);

    /**
     * 根据主键查询整个对象
     */
    ListSong selectByPrimaryKey(Integer id);

    /**
     * 查询所有歌单里面的歌曲
     */
    List<ListSong> allListSong();

    /**
     * 根据歌单id查询所有的歌曲
     */
    List<ListSong> listSongOfSongListId(Integer songListId);
}
















