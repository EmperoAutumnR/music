package com.neu.music.service.impl;

import com.neu.music.mapper.CollectMapper;
import com.neu.music.entity.Collect;
import com.neu.music.service.CollectService;
import com.neu.music.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description: 收藏service实现类
 * @Author: yida
 */
@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectMapper collectMapper;

    /**
     * @Description: 添加收藏
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: dmyqswzg
     * @Date: 2021/12/2
     */
    @Override
    public Object addCollect(HttpServletRequest request) {
        //userId : 用户id | type : 收藏类型（0歌曲1歌单） | songId : 歌曲id
        String userId = request.getParameter("userId");
        String type = request.getParameter("type");
        String songId = request.getParameter("songId");
        if(songId==null || songId.equals("")) {
            return ResponseUtil.fail(0,"收藏歌曲为空");
        }
        //查询某个用户是否已经收藏了某个歌曲
        int existSong = collectMapper.existSongId(Integer.parseInt(userId),Integer.parseInt(songId));
        if(existSong > 0){
            return ResponseUtil.fail(2,"已收藏");
        }
        //保存到收藏的对象中
        Collect collect = new Collect();
        collect.setUserId(Integer.parseInt(userId));
        collect.setType(new Byte(type));
        collect.setSongId(Integer.parseInt(songId));
        int result = collectMapper.insert(collect);
        if(result > 0){
            return ResponseUtil.ok();
        }else {
            return ResponseUtil.fail(0,"收藏失败");
        }
    }

    /**
     * @Description: 删除收藏
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/2
     */
    @Override
    public Object deleteCollect(HttpServletRequest request) {
        String userId = request.getParameter("userId");//用户id
        String songId = request.getParameter("songId");//歌曲id
        int result = collectMapper.deleteByUserIdSongId(Integer.parseInt(userId),Integer.parseInt(songId));
        if (result > 0) {
            return ResponseUtil.ok();
        }else {
            return ResponseUtil.fail();
        }
    }

    /**
     * @Description: 查询所有收藏
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/2
     */
    @Override
    public Object allCollect(HttpServletRequest request) {
        List<Collect> collectList = collectMapper.allCollect();
        return ResponseUtil.okList(collectList);
    }

    /**
     * @Description: 查询某个用户的收藏列表
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/2
     */
    @Override
    public Object collectOfUserId(HttpServletRequest request) {
        String userId = request.getParameter("userId");//用户id
        List<Collect> collectList = collectMapper.collectOfUserId(Integer.parseInt(userId));
        return ResponseUtil.okList(collectList);
    }
}