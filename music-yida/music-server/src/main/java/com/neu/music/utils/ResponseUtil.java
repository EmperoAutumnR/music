package com.neu.music.utils;

import java.util.*;

/**
 * @description:
 * @Author: dmyqswzg
 * @Date: 2021/12/3 15:05
 */
public class ResponseUtil {
    /*登录名 "name"*/
    /*返回码 "code"*/
    /*返回信息 "msg"*/

    public static Object ok() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code",1);
        map.put("msg","成功");
        return map;
    }

    public static Object ok(Object data) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code",1);
        map.put("msg","成功");
        map.put("data",data);
        return map;
    }


    public static Object okList(List list) {
        return list;
    }

    public static Object fail() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code",0);
        map.put("msg","失败");
        return map;
    }

    public static Object fail(int code, String msg) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code",code);
        map.put("msg",msg);
        return map;
    }
}
