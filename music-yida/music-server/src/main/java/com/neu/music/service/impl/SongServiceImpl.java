package com.neu.music.service.impl;

import com.neu.music.entity.Song;
import com.neu.music.mapper.SongMapper;
import com.neu.music.service.SongService;
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
 * @description: 歌曲service实现类
 * @Author: yida
 */
@Service
public class SongServiceImpl implements SongService {
    @Autowired
    private SongMapper songMapper;

    /**
     * @Description: 添加歌曲
     * @Param: [request, mpFile]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/3
     */
    @Override
    public Object addSong(HttpServletRequest request, MultipartFile mpFile) throws IOException {
        //获取前端传来的参数
        String singerId = request.getParameter("singerId").trim();  //所属歌手id
        String name = request.getParameter("name").trim();          //歌名
        String introduction = request.getParameter("introduction").trim();          //简介
        String pic = "/img/songPic/tubiao.jpg";                     //默认图片
        String lyric = request.getParameter("lyric").trim();     //歌词
        //上传歌曲文件
        if(mpFile.isEmpty()){
            return ResponseUtil.fail(0,"歌曲上传失败");
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis()+mpFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"song";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeUrlPath = "/song/"+fileName;
//        try {
            mpFile.transferTo(dest);
            Song song = new Song();
            song.setSingerId(Integer.parseInt(singerId));
            song.setName(name);
            song.setIntroduction(introduction);
            song.setPic(pic);
            song.setLyric(lyric);
            song.setUrl(storeUrlPath);
            int result = songMapper.insert(song);
            Map map = new HashMap();
            if(result > 0){
//                jsonObject.put(Consts.CODE,1);
//                jsonObject.put(Consts.MSG,"保存成功");
//                jsonObject.put("avator",storeUrlPath);
//                return jsonObject;
                map.put("avator",storeUrlPath);
                return ResponseUtil.ok(map);
            }else {
                return ResponseUtil.fail();
            }
//        } catch (IOException e) {
//            jsonObject.put(Consts.CODE,0);
//            jsonObject.put(Consts.MSG,"保存失败"+e.getMessage());
//        }finally {
//            return jsonObject;
//        }
    }

    /**
     * @Description: 根据歌手id查询歌曲
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/3
     */
    @Override
    public Object songOfSingerId(HttpServletRequest request) {
        String singerId = request.getParameter("singerId");
        List<Song> songList = songMapper.songOfSingerId(Integer.parseInt(singerId));
        return ResponseUtil.okList(songList);
    }

    /**
     * @Description: 修改歌曲
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/3
     */
    @Override
    public Object updateSong(HttpServletRequest request) {
        String id = request.getParameter("id").trim();          //主键
        String name = request.getParameter("name").trim();      //歌名
        String introduction = request.getParameter("introduction").trim();//专辑
        String lyric = request.getParameter("lyric").trim();    //歌词
        //保存到歌手的对象中
        Song song = new Song();
        song.setId(Integer.parseInt(id));
        song.setName(name);
        song.setIntroduction(introduction);
        song.setLyric(lyric);
        int result = songMapper.update(song);
        if(result > 0){
            return ResponseUtil.ok();
        }else {
            return ResponseUtil.fail();
        }
    }

    /**
     * @Description: 删除歌曲
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/3
     */
    @Override
    public Object deleteSinger(HttpServletRequest request) {
        //先查询到数据库中对应的文件地址，删除掉它再进行下面的代码
        String id = request.getParameter("id").trim();          //主键
        int result = songMapper.delete(Integer.parseInt(id));
        if(result > 0){
            return ResponseUtil.ok();
        }else {
            return ResponseUtil.fail();
        }
    }

    /**
     * @Description: 更新歌曲图片
     * @Param: [avatorFile, id]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/3
     */
    @Override
    public Object updateSongPic(MultipartFile avatorFile, int id) throws IOException {
        if(avatorFile.isEmpty()){
            return ResponseUtil.fail(0,"文件上传失败");
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"songPic";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "/img/songPic/"+fileName;
//        try {
            avatorFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setPic(storeAvatorPath);
            int result = songMapper.update(song);
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

    /**
     * @Description: 更新歌曲
     * @Param: [avatorFile, id]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/3
     */
    @Override
    public Object updateSongUrl(MultipartFile avatorFile, int id) throws IOException {
        if(avatorFile.isEmpty()){
            return ResponseUtil.fail(0,"文件上传失败");
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"song";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "/song/"+fileName;
//        try {
            avatorFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setUrl(storeAvatorPath);
            int result = songMapper.update(song);
            Map map = new HashMap();
            if(result > 0){
//                jsonObject.put(Consts.CODE,1);
//                jsonObject.put(Consts.MSG,"上传成功");
//                jsonObject.put("avator",storeAvatorPath);
//                return jsonObject;
                map.put("avator",storeAvatorPath);
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

    /**
     * @Description: 根据歌曲id查询歌曲对象
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/3
     */
    @Override
    public Object detail(HttpServletRequest request) {
        String songId = request.getParameter("songId");
        Song song = songMapper.selectByPrimaryKey(Integer.parseInt(songId));
        return ResponseUtil.ok(song);
    }

    /**
     * @Description: 根据歌手名字精确查询歌曲
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/3
     */
    @Override
    public Object songOfSongName(HttpServletRequest request) {
        String songName = request.getParameter("songName");
        List<Song> songList = songMapper.songOfName(songName);
        return ResponseUtil.okList(songList);
    }

    /**
     * @Description: 根据歌手名字模糊查询歌曲
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/3
     */
    @Override
    public Object likeSongOfName(HttpServletRequest request) {
        String songName = request.getParameter("songName");
        List<Song> songList = songMapper.likeSongOfName("%"+songName+"%");
        return ResponseUtil.okList(songList);
    }

    /**
     * @Description: 查询所有歌曲
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: dmyqswzg
     * @Date: 2021/12/3
     */
    @Override
    public Object allSong(HttpServletRequest request) {
        List<Song> songList = songMapper.allSong();
        return ResponseUtil.okList(songList);
    }
}
