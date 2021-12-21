package com.neu.music.service.impl;

import com.neu.music.mapper.SingerMapper;
import com.neu.music.entity.Singer;
import com.neu.music.service.SingerService;
import com.neu.music.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 歌手service实现类
 * @Author: yida
 */
@Service
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerMapper singerMapper;

    /**
     * @Description: 添加歌手
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/3
     */
    @Override
    public Object addSinger(HttpServletRequest request) {
        String name = request.getParameter("name").trim();      //姓名
        String sex = request.getParameter("sex").trim();        //性别
        String pic = request.getParameter("pic").trim();        //头像
        String birth = request.getParameter("birth").trim();    //生日
        String location = request.getParameter("location").trim();//地区
        String introduction = request.getParameter("introduction").trim();//简介
        //把生日转换成Date格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //保存到歌手的对象中
        Singer singer = new Singer();
        singer.setName(name);
        singer.setSex(new Byte(sex));
        singer.setPic(pic);
        singer.setBirth(birthDate);
        singer.setLocation(location);
        singer.setIntroduction(introduction);
        int result = singerMapper.insert(singer);
        if(result > 0){   //保存成功
            return ResponseUtil.ok();
        }else {
            return ResponseUtil.fail();
        }
    }

    /**
     * @Description: 修改歌手
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/3
     */
    @Override
    public Object updateSinger(HttpServletRequest request) {
        String id = request.getParameter("id").trim();          //主键
        String name = request.getParameter("name").trim();      //姓名
        String sex = request.getParameter("sex").trim();        //性别
        String birth = request.getParameter("birth").trim();    //生日
        String location = request.getParameter("location").trim();//地区
        String introduction = request.getParameter("introduction").trim();//简介
        //把生日转换成Date格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //保存到歌手的对象中
        Singer singer = new Singer();
        singer.setId(Integer.parseInt(id));
        singer.setName(name);
        singer.setSex(new Byte(sex));
        singer.setBirth(birthDate);
        singer.setLocation(location);
        singer.setIntroduction(introduction);
        int result = singerMapper.update(singer);
        if(result > 0){
            return ResponseUtil.ok();
        }else {
            return ResponseUtil.fail();
        }
    }

    /**
     * @Description: 删除歌手
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/3
     */
    @Override
    public Object deleteSinger(HttpServletRequest request) {
        String id = request.getParameter("id").trim();          //主键
        int result = singerMapper.delete(Integer.parseInt(id));
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
     * @Author: dmyqswzg
     * @Date: 2021/12/3
     */
    @Override
    public Object selectByPrimaryKey(HttpServletRequest request) {
        String id = request.getParameter("id").trim();          //主键
        Singer singer = singerMapper.selectByPrimaryKey(Integer.parseInt(id));
        return ResponseUtil.ok(singer);
    }

    /**
     * @Description: 查询所有歌手
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/3
     */
    @Override
    public Object allSinger(HttpServletRequest request) {
        List<Singer> singerList = singerMapper.allSinger();
        return ResponseUtil.okList(singerList);
    }

    /**
     * @Description: 根据歌手名字模糊查询列表
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/3
     */
    @Override
    public Object singerOfName(HttpServletRequest request) {
        String name = request.getParameter("name").trim();          //歌手名字
        List<Singer> singerList = singerMapper.singerOfName("%"+name+"%");
        return ResponseUtil.okList(singerList);
    }

    /**
     * @Description: 根据性别查询
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/3
     */
    @Override
    public Object singerOfSex(HttpServletRequest request) {
        String sex = request.getParameter("sex").trim();          //性别
        List<Singer> singerList = singerMapper.singerOfSex(Integer.parseInt(sex));
        return ResponseUtil.okList(singerList);
    }

    /**
     * @Description: 更新歌手图片
     * @Param: [avatorFile, id]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/3
     */
    @Override
    public Object updateSingerPic(MultipartFile avatorFile, int id) throws IOException {
        //判断字符串是否为空
        if(avatorFile.isEmpty()){
            return ResponseUtil.fail(0,"文件上传失败");
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"img"
                +System.getProperty("file.separator")+"singerPic";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "/img/singerPic/"+fileName;
//        try {
            avatorFile.transferTo(dest);
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storeAvatorPath);
            int result = singerMapper.update(singer);
            Map map = new HashMap();
            if(result > 0){
//                jsonObject.put(Consts.CODE,1);
//                jsonObject.put(Consts.MSG,"上传成功");
//                jsonObject.put("pic",storeAvatorPath);
//                return jsonObject;
                map.put("pic",storeAvatorPath);
                return ResponseUtil.ok(map);
            }else {
                return ResponseUtil.fail(0,"上传失败");
            }
//        } catch (IOException e) {
//            jsonObject.put(Consts.CODE,0);
//            jsonObject.put(Consts.MSG,"上传失败"+e.getMessage());
//        }finally {
//            return jsonObject;
//        }
    }
}
