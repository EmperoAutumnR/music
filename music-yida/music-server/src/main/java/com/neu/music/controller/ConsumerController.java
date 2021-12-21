package com.neu.music.controller;

import com.neu.music.service.ConsumerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;

@Api(value = "用户Controller")
@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    @Autowired
    private ConsumerService consumerService;

    @ApiOperation(value = "添加前端用户")
    @PostMapping("/add")
    public Object addConsumer(HttpServletRequest request) throws ParseException {
        return consumerService.addConsumer(request);
    }

    @ApiOperation(value = "修改前端用户")
    @PostMapping("/update")
    public Object updateConsumer(HttpServletRequest request) {
        return consumerService.updateConsumer(request);
    }

    @ApiOperation(value = "删除前端用户")
    @GetMapping("/delete")
    public Object deleteConsumer(HttpServletRequest request){
        return consumerService.deleteConsumer(request);
    }

    @ApiOperation(value = "根据主键查询整个对象")
    @GetMapping("/selectByPrimaryKey")
    public Object selectByPrimaryKey(HttpServletRequest request){
        return consumerService.selectByPrimaryKey(request);
    }

    @ApiOperation(value = "查询所有前端用户")
    @GetMapping("/allConsumer")
    public Object allConsumer(HttpServletRequest request){
        return consumerService.allConsumer(request);
    }

    @ApiOperation(value = "更新前端用户图片")
    @PostMapping("/updateConsumerPic")
    public Object updateConsumerPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id) throws IOException {
        return consumerService.updateConsumer(avatorFile,id);
    }

    @ApiOperation(value = "前端用户登录")
    @PostMapping("/login")
    public Object login(HttpServletRequest request){
        return consumerService.login(request);
    }
}






















