package com.neu.music.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 评分service接口
 * @Author: yida
 */
public interface RankService {

    Object add(HttpServletRequest request);

    Object rankOfSongListId(HttpServletRequest request);
}
