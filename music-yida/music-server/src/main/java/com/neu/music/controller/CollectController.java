package com.neu.music.controller;

import com.neu.music.service.CollectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "用户收藏Controller")
@RestController
@RequestMapping("/collect")
public class CollectController {
    @Autowired
    private CollectService collectService;

    @ApiOperation(value = "添加收藏")
    @PostMapping("/add")
    public Object addCollect(HttpServletRequest request){
        return collectService.addCollect(request);
    }

    @ApiOperation(value = "删除收藏")
    @GetMapping("/delete")
    public Object deleteCollect(HttpServletRequest request){
        return collectService.deleteCollect(request);
    }

    @ApiOperation(value = "查询所有收藏")
    @GetMapping("/allCollect")
    public Object allCollect(HttpServletRequest request){
        return collectService.allCollect(request);
    }

    @ApiOperation(value = "查询某个用户的收藏列表")
    @GetMapping("/collectOfUserId")
    public Object collectOfUserId(HttpServletRequest request){
       return collectService.collectOfUserId(request);
    }

}