package com.neu.music.mapper;

import com.neu.music.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description: 评论
 * @Author: yida
 */
@Repository
public interface CommentMapper {
    /**
     *增加
     */
    int insert(Comment comment);

    /**
     *修改
     */
    int update(Comment comment);

    /**
     * 删除
     */
    int delete(Integer id);

    /**
     * 根据主键查询整个对象
     */
    Comment selectByPrimaryKey(Integer id);

    /**
     * 查询所有评论
     */
    List<Comment> allComment();

    /**
     * 查询某个歌曲下的所有评论
     */
    List<Comment> commentOfSongId(Integer songId);

    /**
     * 查询某个歌单下的所有评论
     */
    List<Comment> commentOfSongListId(Integer songListId);
}
















