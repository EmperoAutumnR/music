package com.neu.music.service.impl;

import com.neu.music.entity.ListSong;
import com.neu.music.mapper.ListSongMapper;
import com.neu.music.service.ListSongService;
import com.neu.music.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description: 歌单包含歌曲service实现类
 * @Author: yida
 */
@Service
public class ListSongServiceImpl implements ListSongService {
    @Autowired
    private ListSongMapper listSongMapper;

    /**
     * @Description: 给歌单添加歌曲
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/3
     */
    @Override
    public Object addListSong(HttpServletRequest request) {
        //获取前端传来的参数
        String songId = request.getParameter("songId").trim();  //歌曲id
        String songListId = request.getParameter("songListId").trim(); //歌单id
        ListSong listSong = new ListSong();
        listSong.setSongId(Integer.parseInt(songId));
        listSong.setSongListId(Integer.parseInt(songListId));
        int result = listSongMapper.insert(listSong);
        if(result > 0){
            return ResponseUtil.ok();
        }else {
            return ResponseUtil.fail();
        }
    }

    @Override
    public Object detail(HttpServletRequest request) {
        /**
         * @Description: 根据歌单id查询歌曲
         * @Param: [request]
         * @return: java.lang.Object
         * @Author: yida
         * @Date: 2021/12/3
         */
        String songListId = request.getParameter("songListId");
        List<ListSong> listSongList = listSongMapper.listSongOfSongListId(Integer.parseInt(songListId));
        return ResponseUtil.okList(listSongList);
    }

    /**
     * @Description: 删除歌单里的歌曲
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/3
     */
    @Override
    public Object delete(HttpServletRequest request) {
        String songId = request.getParameter("songId").trim();                 //歌曲id
        String songListId = request.getParameter("songListId").trim();        //歌单id
        int result = listSongMapper.deleteBySongIdAndSongListId(Integer.parseInt(songId),Integer.parseInt(songListId));
        if (result > 0) {
            return ResponseUtil.ok();
        }else {
            return ResponseUtil.fail();
        }
    }
}
