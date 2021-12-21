package com.neu.music.controller;

import com.neu.music.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "用户评论Controller")
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "添加评论")
    @PostMapping("/add")
    public Object addComment(HttpServletRequest request){
        return commentService.addComment(request);
    }

    @ApiOperation(value = "修改评论")
    @PostMapping("/update")
    public Object updateComment(HttpServletRequest request){
        return commentService.updateComment(request);
    }

    @ApiOperation(value = "删除评论")
    @GetMapping("/delete")
    public Object deleteComment(HttpServletRequest request){
       return commentService.deleteComment(request);
    }

    @ApiOperation(value = "根据主键查询整个对象")
    @GetMapping("/selectByPrimaryKey")
    public Object selectByPrimaryKey(HttpServletRequest request){
        return commentService.selectByPrimaryKey(request);
    }

    @ApiOperation(value = "查询所有评论")
    @GetMapping("/allComment")
    public Object allComment(HttpServletRequest request){
        return commentService.allComment(request);
    }

    @ApiOperation(value = "查询某个歌曲下的所有评论")
    @GetMapping("/commentOfSongId")
    public Object commentOfSongId(HttpServletRequest request){
        return commentService.commentOfSongId(request);
    }

    @ApiOperation(value = "查询某个歌单下的所有评论")
    @GetMapping("/commentOfSongListId")
    public Object commentOfSongListId(HttpServletRequest request){
        return commentService.commentOfSongListId(request);
    }

    @ApiOperation(value = "给某个评论点赞")
    @PostMapping("/like")
    public Object like(HttpServletRequest request){
       return commentService.like(request);
    }

}






















