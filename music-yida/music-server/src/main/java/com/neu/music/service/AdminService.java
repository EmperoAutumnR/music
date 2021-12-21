package com.neu.music.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @description: 管理员service接口
 * @Author: yida
 */
public interface AdminService {
    Object loginStatus(HttpServletRequest request, HttpSession session);
}
