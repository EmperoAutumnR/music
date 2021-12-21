package com.neu.music.controller;

import com.neu.music.service.ListSongService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "歌单包含歌曲Controller")
@RestController
@RequestMapping("/listSong")
public class ListSongController {
    @Autowired
    private ListSongService listSongService;

    @ApiOperation(value = "给歌单添加歌曲")
    @PostMapping("/add")
    public Object addListSong(HttpServletRequest request){
        return listSongService.addListSong(request);
    }

    @ApiOperation(value = "根据歌单id查询歌曲")
    @GetMapping("/detail")
    public Object detail(HttpServletRequest request){
        return listSongService.detail(request);
    }

    @ApiOperation(value = "删除歌单里的歌曲")
    @GetMapping("/delete")
    public Object delete(HttpServletRequest request){
        return listSongService.delete(request);
    }

}




















