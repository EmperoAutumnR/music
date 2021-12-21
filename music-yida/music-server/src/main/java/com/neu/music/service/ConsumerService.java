package com.neu.music.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;

/**
 * @description: 用户service接口
 * @Author: yida
 */
public interface ConsumerService {

    Object addConsumer(HttpServletRequest request) throws ParseException;

    Object updateConsumer(HttpServletRequest request);

    Object deleteConsumer(HttpServletRequest request);

    Object selectByPrimaryKey(HttpServletRequest request);

    Object allConsumer(HttpServletRequest request);

    Object updateConsumer(MultipartFile avatorFile, int id) throws IOException;

    Object login(HttpServletRequest request);
}
