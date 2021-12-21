package com.neu.music.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 歌单包含歌曲service接口
 * @Author: yida
 */
public interface ListSongService {

    Object addListSong(HttpServletRequest request);

    Object detail(HttpServletRequest request);

    Object delete(HttpServletRequest request);
}
