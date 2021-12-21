package com.neu.music.controller;

import com.neu.music.service.SongListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Api(value = "歌单Controller")
@RestController
@RequestMapping("/songList")
public class SongListController {
    @Autowired
    private SongListService songListService;

    @ApiOperation(value = "添加歌单")
    @PostMapping("/add")
    public Object addSongList(HttpServletRequest request){
        return songListService.addSongList(request);
    }

    @ApiOperation(value = "修改歌单")
    @PostMapping("/update")
    public Object updateSongList(HttpServletRequest request){
        return songListService.updateSongList(request);
    }

    @ApiOperation(value = "删除歌单")
    @GetMapping("/delete")
    public Object deleteSongList(HttpServletRequest request){
        return songListService.deleteSongList(request);
    }

    @ApiOperation(value = "根据主键查询整个对象")
    @GetMapping("/selectByPrimaryKey")
    public Object selectByPrimaryKey(HttpServletRequest request){
        return songListService.selectByPrimaryKey(request);
    }

    @ApiOperation(value = "查询所有歌单")
    @GetMapping("/allSongList")
    public Object allSongList(HttpServletRequest request){
        return songListService.allSongList(request);
    }

    @ApiOperation(value = "根据标题精确查询歌单列表")
    @GetMapping("/songListOfTitle")
    public Object songListOfName(HttpServletRequest request){
        return songListService.songListOfName(request);
    }

    @ApiOperation(value = "根据标题模糊查询歌单列表")
    @GetMapping("/likeTitle")
    public Object likeTitle(HttpServletRequest request){
        return songListService.likeTitle(request);
    }

    @ApiOperation(value = "根据风格模糊查询歌单列表")
    @GetMapping("/likeStyle")
    public Object likeStyle(HttpServletRequest request){
        return songListService.likeStyle(request);
    }

    @ApiOperation(value = "更新歌单图片")
    @PostMapping("/updateSongListPic")
    public Object updateSongListPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id) throws IOException {
        return songListService.updateSongListPic(avatorFile,id);
    }
}






















