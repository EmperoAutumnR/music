package com.neu.music.controller;

import com.neu.music.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Api(value = "管理员Controller")
@RestController
@RequestMapping("admin/login")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "判断是否登录成功")
    @PostMapping("/status")
    public Object loginStatus(HttpServletRequest request, HttpSession session){
        return adminService.loginStatus(request,session);
    }
}






















