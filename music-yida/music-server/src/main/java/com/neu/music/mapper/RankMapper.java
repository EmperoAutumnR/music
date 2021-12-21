package com.neu.music.mapper;

import com.neu.music.entity.Rank;
import org.springframework.stereotype.Repository;

/**
 * @description: 评分
 * @Author: yida
 */
@Repository
public interface RankMapper {
    /**
     *增加
     */
    int insert(Rank rank);

    /**
     * 查总分
     */
    int selectScoreSum(Integer songListId);

    /**
     * 查总评分人数
     */
    int selectRankNum(Integer songListId);
}
















