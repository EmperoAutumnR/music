package com.neu.music.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 收藏service接口
 * @Author: yida
 */
public interface CollectService {
    Object addCollect(HttpServletRequest request);

    Object deleteCollect(HttpServletRequest request);

    Object allCollect(HttpServletRequest request);

    Object collectOfUserId(HttpServletRequest request);
}