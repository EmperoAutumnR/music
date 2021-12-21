package com.neu.music.controller;

import com.neu.music.service.SongService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Api(value = "歌曲Controller")
@RestController
@RequestMapping("/song")
public class SongController {
    @Autowired
    private SongService songService;

    @ApiOperation(value = "添加歌曲")
    @PostMapping("/add")
    public Object addSong(HttpServletRequest request, @RequestParam("file")MultipartFile mpFile) throws IOException {
        return songService.addSong(request,mpFile);
    }

    @ApiOperation(value = "根据歌手id查询歌曲")
    @GetMapping("/singer/detail")
    public Object songOfSingerId(HttpServletRequest request){
        return songService.songOfSingerId(request);
    }

    @ApiOperation(value = "修改歌曲")
    @PostMapping("/update")
    public Object updateSong(HttpServletRequest request){
        return songService.updateSong(request);
    }

    @ApiOperation(value = "删除歌曲")
    @GetMapping("/delete")
    public Object deleteSinger(HttpServletRequest request){
        return songService.deleteSinger(request);
    }

    @ApiOperation(value = "更新歌曲图片")
    @PostMapping("/updateSongPic")
    public Object updateSongPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id) throws IOException {
        return songService.updateSongPic(avatorFile,id);
    }

    @ApiOperation(value = "更新歌曲")
    @PostMapping("/updateSongUrl")
    public Object updateSongUrl(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id) throws IOException {
        return songService.updateSongUrl(avatorFile,id);
    }

    @ApiOperation(value = "根据歌曲id查询歌曲对象")
    @GetMapping("/detail")
    public Object detail(HttpServletRequest request){
        return songService.detail(request);
    }

    @ApiOperation(value = "根据歌手名字精确查询歌曲")
    @GetMapping("/songOfSongName")
    public Object songOfSongName(HttpServletRequest request){
        return songService.songOfSongName(request);
    }

    @ApiOperation(value = "根据歌手名字模糊查询歌曲")
    @GetMapping("/likeSongOfName")
    public Object likeSongOfName(HttpServletRequest request){
        return songService.likeSongOfName(request);
    }

    @ApiOperation(value = "查询所有歌曲")
    @GetMapping("/allSong")
    public Object allSong(HttpServletRequest request){
        return songService.allSong(request);
    }

}




















