package com.neu.music.service.impl;

import com.neu.music.mapper.CommentMapper;
import com.neu.music.entity.Comment;
import com.neu.music.service.CommentService;
import com.neu.music.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description: 评论service实现类
 * @Author: yida
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    /**
     * @Description: 添加评论
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/2
     */
    @Override
    public Object addComment(HttpServletRequest request) {
        String userId = request.getParameter("userId");//用户id
        String type = request.getParameter("type");//评论类型（0歌曲1歌单）
        String songId = request.getParameter("songId");//歌曲id
        String songListId = request.getParameter("songListId");//歌单id
        String content = request.getParameter("content").trim();//评论内容

        //保存到评论的对象中
        Comment comment = new Comment();
        comment.setUserId(Integer.parseInt(userId));
        comment.setType(new Byte(type));
        if(new Byte(type) ==0){
            comment.setSongId(Integer.parseInt(songId));
        }else{
            comment.setSongListId(Integer.parseInt(songListId));
        }
        comment.setContent(content);
        int result = commentMapper.insert(comment);
        if(result > 0){   //保存成功
            return ResponseUtil.ok();
        }else {
            return ResponseUtil.fail();
        }
    }

    /**
     * @Description: 修改评论
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/2
     */
    @Override
    public Object updateComment(HttpServletRequest request) {
        String id = request.getParameter("id").trim();                   //主键
        String userId = request.getParameter("userId").trim();           //用户id
        String type = request.getParameter("type").trim();               //评论类型（0歌曲1歌单）
        String songId = request.getParameter("songId").trim();           //歌曲id
        String songListId = request.getParameter("songListId").trim();   //歌单id
        String content = request.getParameter("content").trim();         //评论内容

        //保存到评论的对象中
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setUserId(Integer.parseInt(userId));
        comment.setType(new Byte(type));
        if(songId!=null&&songId.equals("")){
            songId = null;
        }else {
            comment.setSongId(Integer.parseInt(songId));
        }
        if(songListId!=null&&songListId.equals("")){
            songListId = null;
        }else {
            comment.setSongListId(Integer.parseInt(songListId));
        }
        comment.setContent(content);
        int result = commentMapper.update(comment);
        if(result > 0){   //保存成功
            return ResponseUtil.ok();
        }else {
            return ResponseUtil.fail();
        }
    }

    /**
     * @Description: 删除评论
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/2
     */
    @Override
    public Object deleteComment(HttpServletRequest request) {
        String id = request.getParameter("id").trim();          //主键
        int result = commentMapper.delete(Integer.parseInt(id));
        if (result > 0) {
            return ResponseUtil.ok();
        }else {
            return ResponseUtil.fail();
        }
    }

    /**
     * @Description: 根据主键查询整个对象
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/2
     */
    @Override
    public Object selectByPrimaryKey(HttpServletRequest request) {
        String id = request.getParameter("id").trim();          //主键
        Comment comment = commentMapper.selectByPrimaryKey(Integer.parseInt(id));
        return ResponseUtil.ok(comment);
    }

    /**
     * @Description: 查询所有评论
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/2
     */
    @Override
    public Object allComment(HttpServletRequest request) {
        List<Comment> comments = commentMapper.allComment();
        return ResponseUtil.okList(comments);
    }

    /**
     * @Description: 查询某个歌曲下的所有评论
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/2
     */
    @Override
    public Object commentOfSongId(HttpServletRequest request) {
        String songId = request.getParameter("songId");          //歌曲id
        List<Comment> comments = commentMapper.commentOfSongId(Integer.parseInt(songId));
        return ResponseUtil.okList(comments);
    }

    /**
     * @Description: 查询某个歌单下的所有评论
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/2
     */
    @Override
    public Object commentOfSongListId(HttpServletRequest request) {
        String songListId = request.getParameter("songListId");          //歌曲id
        List<Comment> comments = commentMapper.commentOfSongListId(Integer.parseInt(songListId));
        return ResponseUtil.okList(comments);
    }

    /**
     * @Description: 给某个评论点赞
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/3
     */
    @Override
    public Object like(HttpServletRequest request) {
        String id = request.getParameter("id").trim();           //主键
        String up = request.getParameter("up").trim();           //用户id

        //保存到评论的对象中
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setUp(Integer.parseInt(up));

        int result = commentMapper.update(comment);
        if(result >0){   //保存成功
            return ResponseUtil.ok();
        }else {
            return ResponseUtil.fail();
        }
    }
}
