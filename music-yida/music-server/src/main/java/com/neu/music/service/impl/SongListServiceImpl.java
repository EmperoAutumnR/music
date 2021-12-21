package com.neu.music.service.impl;

import com.neu.music.mapper.SongListMapper;
import com.neu.music.entity.SongList;
import com.neu.music.service.SongListService;
import com.neu.music.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 歌单service实现类
 * @Author: yida
 */
@Service
public class SongListServiceImpl implements SongListService {

    @Autowired
    private SongListMapper songListMapper;

    /**
     * @Description: 添加歌单
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/3
     */
    @Override
    public Object addSongList(HttpServletRequest request) {
        String title = request.getParameter("title").trim();      //标题
        String pic = request.getParameter("pic").trim();        //歌单图片
        String introduction = request.getParameter("introduction").trim();//简介
        String style = request.getParameter("style").trim();    //风格

        //保存到歌单的对象中
        SongList songList = new SongList();
        songList.setTitle(title);
        songList.setPic(pic);
        songList.setIntroduction(introduction);
        songList.setStyle(style);
        int result = songListMapper.insert(songList);
        if(result > 0){   //保存成功
            return ResponseUtil.ok();
        }else {
            return ResponseUtil.fail();
        }
    }

    /**
     * @Description: 修改歌单
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/3
     */
    @Override
    public Object updateSongList(HttpServletRequest request) {
        String id = request.getParameter("id").trim();          //主键
        String title = request.getParameter("title").trim();      //标题
        String introduction = request.getParameter("introduction").trim();//简介
        String style = request.getParameter("style").trim();    //风格
        //保存到歌单的对象中
        SongList songList = new SongList();
        songList.setId(Integer.parseInt(id));
        songList.setTitle(title);
        songList.setIntroduction(introduction);
        songList.setStyle(style);
        int result = songListMapper.update(songList);
        if(result > 0){
            return ResponseUtil.ok();
        }else {
            return ResponseUtil.fail();
        }
    }

    /**
     * @Description: 删除歌单
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/3
     */
    @Override
    public Object deleteSongList(HttpServletRequest request) {
        String id = request.getParameter("id").trim();          //主键
        int result = songListMapper.delete(Integer.parseInt(id));
        if(result > 0){   //保存成功
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
     * @Date: 2021/12/3
     */
    @Override
    public Object selectByPrimaryKey(HttpServletRequest request) {
        String id = request.getParameter("id").trim();          //主键
        SongList songList = songListMapper.selectByPrimaryKey(Integer.parseInt(id));
        Map map = new HashMap();
        map.put("data",songList);
        return ResponseUtil.ok(map);
    }

    /**
     * @Description: 查询所有歌单
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/3
     */
    @Override
    public Object allSongList(HttpServletRequest request) {
        List<SongList> songLists = songListMapper.allSongList();
        return ResponseUtil.okList(songLists);
    }

    /**
     * @Description: 根据标题精确查询歌单列表
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/3
     */
    @Override
    public Object songListOfName(HttpServletRequest request) {
        String title = request.getParameter("title").trim();          //歌单标题
        List<SongList> songLists = songListMapper.songListOfTitle(title);
        return ResponseUtil.okList(songLists);
    }

    /**
     * @Description: 根据标题模糊查询歌单列表
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/3
     */
    @Override
    public Object likeTitle(HttpServletRequest request) {
        String title = request.getParameter("title").trim();          //歌单标题
        List<SongList> songLists = songListMapper.likeTitle("%"+title+"%");
        return ResponseUtil.okList(songLists);
    }

    /**
     * @Description: 根据风格模糊查询歌单列表
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/3
     */
    @Override
    public Object likeStyle(HttpServletRequest request) {
        String style = request.getParameter("style").trim();          //歌单风格
        List<SongList> songLists = songListMapper.likeStyle("%"+style+"%");
        return ResponseUtil.okList(songLists);
    }

    /**
     * @Description: 更新歌单图片
     * @Param: [avatorFile, id]
     * @return: java.lang.Object
     * @Author: dmyqswzg
     * @Date: 2021/12/3
     */
    @Override
    public Object updateSongListPic(MultipartFile avatorFile, int id) throws IOException {
        if(avatorFile.isEmpty()){
            return ResponseUtil.fail(0,"文件上传失败");
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"songListPic";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "/img/songListPic/"+fileName;
//        try {
            avatorFile.transferTo(dest);
            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(storeAvatorPath);
            int result = songListMapper.update(songList);
            Map map = new HashMap();
            if(result > 0){
//                jsonObject.put(Consts.CODE,1);
//                jsonObject.put(Consts.MSG,"上传成功");
//                jsonObject.put("pic",storeAvatorPath);
//                return jsonObject;
                map.put("pic",storeAvatorPath);
                return ResponseUtil.ok(map);
            }else {
                return ResponseUtil.fail();
            }
//        } catch (IOException e) {
//            jsonObject.put(Consts.CODE,0);
//            jsonObject.put(Consts.MSG,"上传失败"+e.getMessage());
//        }finally {
//            return jsonObject;
//        }
    }
}
