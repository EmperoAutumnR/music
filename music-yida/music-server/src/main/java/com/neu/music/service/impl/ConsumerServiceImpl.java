package com.neu.music.service.impl;

import com.neu.music.entity.Consumer;
import com.neu.music.mapper.ConsumerMapper;
import com.neu.music.service.ConsumerService;
import com.neu.music.utils.MD5Util;
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
 * @description: 用户service实现类
 * @Author: yida
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Autowired
    private ConsumerMapper consumerMapper;

    /**
     * @Description: 添加前端用户
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/3
     */
    @Override
    public Object addConsumer(HttpServletRequest request) throws ParseException {
        String username = request.getParameter("username").trim();     //账号
        String password = MD5Util.getMD5Str(request.getParameter("password").trim());     //密码进行加密处理
        String sex = request.getParameter("sex").trim();               //性别
        String phoneNum = request.getParameter("phoneNum").trim();     //手机号
        String email = request.getParameter("email").trim();           //电子邮箱
        String birth = request.getParameter("birth").trim();           //生日
        String introduction = request.getParameter("introduction").trim();//签名
        String location = request.getParameter("location").trim();      //地区
        String avator = request.getParameter("avator").trim();          //头像地址
        if(username==null||username.equals("")){
            return ResponseUtil.fail(0,"用户名不能为空");
        }
        Consumer consumerName = consumerMapper.getByUsername(username);
        if(consumerName!=null){
            return ResponseUtil.fail(0,"用户名已存在");
        }
        if(password==null||password.equals("")){
            return ResponseUtil.fail(0,"密码不能为空");
        }
        //把生日转换成Date格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
//        try {
            birthDate = dateFormat.parse(birth);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        //保存到前端用户的对象中
        Consumer consumer = new Consumer();
        consumer.setUsername(username);
        consumer.setPassword(password);
        consumer.setSex(new Byte(sex));
        consumer.setPhoneNum(phoneNum);
        consumer.setEmail(email);
        consumer.setBirth(birthDate);
        consumer.setIntroduction(introduction);
        consumer.setLocation(location);
        consumer.setAvator(avator);
        int result = consumerMapper.insert(consumer);
        if(result > 0){
            return ResponseUtil.ok();
        }else {
            return ResponseUtil.fail();
        }
    }

    /**
     * @Description: 修改前端用户
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/3
     */
    @Override
    public Object updateConsumer(HttpServletRequest request) {
        String id = request.getParameter("id").trim();          //主键
        String username = request.getParameter("username").trim();     //账号
        String password = MD5Util.getMD5Str(request.getParameter("password").trim());     //密码
        String sex = request.getParameter("sex").trim();               //性别
        String phoneNum = request.getParameter("phoneNum").trim();     //手机号
        String email = request.getParameter("email").trim();           //电子邮箱
        String birth = request.getParameter("birth").trim();           //生日
        String introduction = request.getParameter("introduction").trim();//签名
        String location = request.getParameter("location").trim();      //地区

        if(username==null||username.equals("")){
            return ResponseUtil.fail(0,"用户名不能为空");
        }
        if(password==null||password.equals("")){
            return ResponseUtil.fail(0,"密码不能为空");
        }
        //把生日转换成Date格式
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = new Date();
        try {
            birthDate = dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //保存到前端用户的对象中
        Consumer consumer = new Consumer();
        consumer.setId(Integer.parseInt(id));
        consumer.setUsername(username);
        consumer.setPassword(password);
        consumer.setSex(new Byte(sex));
        consumer.setPhoneNum(phoneNum);
        consumer.setEmail(email);
        consumer.setBirth(birthDate);
        consumer.setIntroduction(introduction);
        consumer.setLocation(location);
        int result = consumerMapper.update(consumer);
        if(result > 0){
            return ResponseUtil.ok();
        }else {
            return ResponseUtil.fail();
        }
    }

    /**
     * @Description: 删除前端用户
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/3
     */
    @Override
    public Object deleteConsumer(HttpServletRequest request) {
        String id = request.getParameter("id").trim();          //主键
        int result = consumerMapper.delete(Integer.parseInt(id));
        if(result > 0){
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
        Consumer consumer = consumerMapper.selectByPrimaryKey(Integer.parseInt(id));
        return ResponseUtil.ok(consumer);
    }

    /**
     * @Description: 查询所有前端用户
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/3
     */
    @Override
    public Object allConsumer(HttpServletRequest request) {
        List<Consumer> consumerList = consumerMapper.allConsumer();
        return ResponseUtil.okList(consumerList);
    }

    /**
     * @Description: 更新前端用户图片
     * @Param: [avatorFile, id]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/3
     */
    @Override
    public Object updateConsumer(MultipartFile avatorFile, int id) throws IOException {
        if(avatorFile.isEmpty()){
            return ResponseUtil.fail(0,"文件上传失败");
        }
        //文件名=当前时间到毫秒+原来的文件名
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        //文件路径
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"avatorImages";
        //如果文件路径不存在，新增该路径
        File file1 = new File(filePath);
        if(!file1.exists()){
            file1.mkdir();
        }
        //实际的文件地址
        File dest = new File(filePath+System.getProperty("file.separator")+fileName);
        //存储到数据库里的相对文件地址
        String storeAvatorPath = "/avatorImages/"+fileName;
//        try {
            avatorFile.transferTo(dest);
            Consumer consumer = new Consumer();
            consumer.setId(id);
            consumer.setAvator(storeAvatorPath);
            int result = consumerMapper.update(consumer);
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
     * @Description: 前端用户登录
     * @Param: [request]
     * @return: java.lang.Object
     * @Author: dmyqswzg
     * @Date: 2021/12/3
     */
    @Override
    public Object login(HttpServletRequest request) {
        String username = request.getParameter("username").trim();     //账号
        String password = MD5Util.getMD5Str(request.getParameter("password").trim());     //密码
//        String password = request.getParameter("password").trim();     //密码
        if(username==null||username.equals("")){
            return ResponseUtil.fail(0,"用户名不能为空");
        }
        if(password==null||password.equals("")){
            return ResponseUtil.fail(0,"密码不能为空");
        }
        //保存到前端用户的对象中
        Consumer consumer = new Consumer();
        consumer.setUsername(username);
        consumer.setPassword(password);
        int result = consumerMapper.verifyPassword(username,password);
        if(result > 0){   //验证成功
//            map.put(Consts.CODE,1);
//            map.put(Consts.MSG,"登录成功");
//            map.put("userMsg",consumerMapper.getByUsername(username));
//            return map;
            Consumer export = consumerMapper.getByUsername(username);
            return ResponseUtil.ok(export);
        }else {
            return ResponseUtil.fail(0,"用户名或密码错误");
        }
    }
}
