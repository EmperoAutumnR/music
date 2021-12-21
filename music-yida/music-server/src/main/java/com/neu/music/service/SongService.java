package com.neu.music.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @description: 歌曲service接口
 * @Author: yida
 */
public interface SongService {
    Object addSong(HttpServletRequest request, MultipartFile mpFile) throws IOException ;

    Object songOfSingerId(HttpServletRequest request);

    Object updateSong(HttpServletRequest request);

    Object deleteSinger(HttpServletRequest request);

    Object updateSongPic(MultipartFile avatorFile, int id) throws IOException ;

    Object updateSongUrl(MultipartFile avatorFile, int id) throws IOException;

    Object detail(HttpServletRequest request);

    Object songOfSongName(HttpServletRequest request);

    Object likeSongOfName(HttpServletRequest request);

    Object allSong(HttpServletRequest request);
}
