package com.neu.music.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 评论service接口
 * @Author: yida
 */
public interface CommentService {
    Object addComment(HttpServletRequest request);

    Object updateComment(HttpServletRequest request);

    Object deleteComment(HttpServletRequest request);

    Object selectByPrimaryKey(HttpServletRequest request);

    Object allComment(HttpServletRequest request);

    Object commentOfSongId(HttpServletRequest request);

    Object commentOfSongListId(HttpServletRequest request);

    Object like(HttpServletRequest request);
}
