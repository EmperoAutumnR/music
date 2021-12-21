package com.neu.music.service.impl;

import com.neu.music.mapper.RankMapper;
import com.neu.music.entity.Rank;
import com.neu.music.service.RankService;
import com.neu.music.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 评分service实现类
 * @Author: yida
 */
@Service
public class RankServiceImpl implements RankService {

    @Autowired
    private RankMapper rankMapper;

    /**
     * @Description: 新增评价
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: ydia
     * @Date: 2021/12/3
     */
    @Override
    public Object add(HttpServletRequest request) {
        String songListId = request.getParameter("songListId");
        String consumerId = request.getParameter("consumerId");
        String score = request.getParameter("score");

        Rank rank = new Rank();
        rank.setSongListId(Integer.parseInt(songListId));
        rank.setConsumerId(Integer.parseInt(consumerId));
        rank.setScore(Integer.parseInt(score));
        int result = rankMapper.insert(rank);
        if(result > 0){
            return ResponseUtil.ok();
        }else {
            return ResponseUtil.fail();
        }
    }

    /**
     * @Description: 计算平均分
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/3
     */
    @Override
    public Object rankOfSongListId(HttpServletRequest request) {
        String songListId = request.getParameter("songListId");
        int rankNum = rankMapper.selectRankNum(Integer.parseInt(songListId));
        if(rankNum != 0){
            int result = rankMapper.selectScoreSum(Integer.parseInt(songListId));
            if (result > 0) {
                return ResponseUtil.ok(result/rankNum);
            }else {
                return ResponseUtil.fail();
            }
        }else {
            return ResponseUtil.ok(5);
        }

    }
}