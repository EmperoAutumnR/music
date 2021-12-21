package com.neu.music.controller;

import com.neu.music.service.RankService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "用户评分Controller")
@RestController
@RequestMapping("/rank")
public class RankController {
    @Autowired
    private RankService rankService;

    @ApiOperation(value = "新增评价")
    @PostMapping("/rank/add")
    public Object add(HttpServletRequest request){
        return rankService.add(request);
    }

    @ApiOperation(value = "计算平均分")
    @GetMapping("")
    public Object rankOfSongListId(HttpServletRequest request){
        return rankService.rankOfSongListId(request);
    }
}






















