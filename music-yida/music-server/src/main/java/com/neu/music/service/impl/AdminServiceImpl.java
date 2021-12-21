package com.neu.music.service.impl;

import com.neu.music.mapper.AdminMapper;
import com.neu.music.service.AdminService;
import com.neu.music.utils.MD5Util;
import com.neu.music.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @description: 管理员service实现类
 * @Author: yida
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    /**
     * @Description: 判断是否登录成功
     * @Param: [request, session]
     * @return: java.lang.Object
     * @Author: yida
     * @Date: 2021/12/2
     */
    @Override
    public Object loginStatus(HttpServletRequest request, HttpSession session) {
        //将输入框的用户名跟密码提取
        String name = request.getParameter("name");
//        String password = request.getParameter("password");
        String password = MD5Util.getMD5Str(request.getParameter("password").trim());
        //验证密码正确性
        int result = adminMapper.verifyPassword(name,password);
        if(result > 0){
            session.setAttribute("name",name);
            return ResponseUtil.ok();
        }else {
            return ResponseUtil.fail(0,"用户名或密码错误");
        }
    }

}
