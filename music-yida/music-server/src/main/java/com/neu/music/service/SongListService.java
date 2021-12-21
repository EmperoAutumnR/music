package com.neu.music.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @description: 歌单service接口
 * @Author: yida
 */
public interface SongListService {
    Object addSongList(HttpServletRequest request);

    Object updateSongList(HttpServletRequest request);

    Object deleteSongList(HttpServletRequest request);

    Object selectByPrimaryKey(HttpServletRequest request);

    Object allSongList(HttpServletRequest request);

    Object songListOfName(HttpServletRequest request);

    Object likeTitle(HttpServletRequest request);

    Object likeStyle(HttpServletRequest request);

    Object updateSongListPic(MultipartFile avatorFile, int id) throws IOException;
}
