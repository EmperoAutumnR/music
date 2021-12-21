package com.neu.music.controller;

import com.neu.music.service.SingerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Api(value = "歌手Controller")
@RestController
@RequestMapping("/singer")
public class SingerController {

    @Autowired
    private SingerService singerService;

    @ApiOperation(value = "添加歌手")
    @PostMapping("/add")
    public Object addSinger(HttpServletRequest request){
        return singerService.addSinger(request);
    }

    @ApiOperation(value = "修改歌手")
    @PostMapping("/update")
    public Object updateSinger(HttpServletRequest request){
        return singerService.updateSinger(request);
    }

    @ApiOperation(value = "删除歌手")
    @GetMapping("/delete")
    public Object deleteSinger(HttpServletRequest request){
        return singerService.deleteSinger(request);
    }

    @ApiOperation(value = "根据主键查询整个对象")
    @GetMapping("/selectByPrimaryKey")
    public Object selectByPrimaryKey(HttpServletRequest request){
        return singerService.selectByPrimaryKey(request);
    }

    @ApiOperation(value = "查询所有歌手")
    @GetMapping("/allSinger")
    public Object allSinger(HttpServletRequest request){
        return singerService.allSinger(request);
    }

    @ApiOperation(value = "根据歌手名字模糊查询列表")
    @GetMapping("/singerOfName")
    public Object singerOfName(HttpServletRequest request){
        return singerService.singerOfName(request);
    }

    @ApiOperation(value = "根据性别查询")
    @GetMapping("/singerOfSex")
    public Object singerOfSex(HttpServletRequest request){
        return singerService.singerOfSex(request);
    }

    @ApiOperation(value = "更新歌手图片")
    @PostMapping("/updateSingerPic")
    public Object updateSingerPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id) throws IOException{
        return singerService.updateSingerPic(avatorFile,id);
    }
}






















