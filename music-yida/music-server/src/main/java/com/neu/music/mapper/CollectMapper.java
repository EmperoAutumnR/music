package com.neu.music.mapper;

import com.neu.music.entity.Collect;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description: 收藏
 * @Author: yida
 */
@Repository
public interface CollectMapper {
    /*新增收藏*/
    int insert(Collect collect);

    /**
     * 删除
     */
    int delete(Integer id);

    /**
     * 根据用户id和歌曲id删除
     */
    int deleteByUserIdSongId(@Param("userId") Integer userId, @Param("songId") Integer songId);

    /**
     * @Description: 查询某个用户是否已经收藏了某个歌曲
     * @Param: [userId, songId]
     * @return: int
     * @Author: yida
     * @Date: 2021/12/2
     */
    int existSongId(@Param("userId") Integer userId, @Param("songId") Integer songId);

    /**
     * @Description: 查询所有收藏
     * @Param: []
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/2
     */
    List<Collect> allCollect();

    /**
     * @Description: 查询某个用户的收藏列表
     * @Param: [parseInt]
     * @return: java.util.List<com.neu.music.entity.Collect>
     * @Author: yida
     * @Date: 2021/12/2
     */
    List<Collect> collectOfUserId(int parseInt);

}
















