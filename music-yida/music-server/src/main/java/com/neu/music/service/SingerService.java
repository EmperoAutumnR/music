package com.neu.music.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @description: 歌手service接口
 * @Author: yida
 */
public interface SingerService {
    Object addSinger(HttpServletRequest request);

    Object updateSinger(HttpServletRequest request);

    Object deleteSinger(HttpServletRequest request);

    Object selectByPrimaryKey(HttpServletRequest request);

    Object allSinger(HttpServletRequest request);

    Object singerOfName(HttpServletRequest request);

    Object singerOfSex(HttpServletRequest request);

    Object updateSingerPic(MultipartFile avatorFile, int id) throws IOException;
}
