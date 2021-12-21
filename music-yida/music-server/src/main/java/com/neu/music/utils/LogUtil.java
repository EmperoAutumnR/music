//package com.neu.music.utils;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.annotation.PostConstruct;
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @description: 日志工具类
// * @Author: dmyqswzg
// * @Date: 2021/11/15 8:58
// */
//@Component
//public class LogUtil {
//    /**
//     * type 操作分类:
//     * 0一般日志：用户觉得需要查看的一般操作日志，建议是默认的日志级别;
//     * 1安全日志：用户安全相关的操作日志，例如登录、删除管理员;
//     * 2订单日志：用户交易相关的操作日志，例如订单发货、退款;
//     * 3其他日志：如果以上三种不合适，可以选择其他日志，建议是优先级最低的日志级别
//     * status 操作状态:1-成功 0-失败
//     */
//    public final Integer LOG_TYPE_GENERAL = 0;
//    public final Integer LOG_TYPE_AUTH = 1;
//    public final Integer LOG_TYPE_ORDER = 2;
//    public final Integer LOG_TYPE_OTHER = 3;
//
//    public final Integer SUCCESS_STATUS = 1;
//    public final Integer FAIL_STATUS = 0;
//
//    @Autowired
//    private ShoppingLogMapper shoppingLogMapper;
//    @Autowired
//    private ShoppingAdminMapper shoppingAdminMapper;
//    /*解决Autowired自动注入不成功
//    1.静态初始化当前对象
//    2.创建初始化方法，使用@PostConstruct，将需要注入的类添加到静态变量中*/
//    public static LogUtil logUtil;
//    @PostConstruct
//    public void init(){
//        logUtil = this;
//        logUtil.shoppingLogMapper = this.shoppingLogMapper;
//        logUtil.shoppingAdminMapper = this.shoppingAdminMapper;
//    }
//
//    //TODO 一般日志
//    public void generalSuccess(String action, String result){
//        saveByToken(action,result,LOG_TYPE_GENERAL,SUCCESS_STATUS);
//    }
//
//    public void generalError( String action, String result) {
//        saveByToken(action,result,LOG_TYPE_GENERAL,FAIL_STATUS);
//}
//    //TODO 安全日志
//    public void authSuccess(String admin, String action, String result){
//        saveLog(admin,action,result,LOG_TYPE_AUTH,SUCCESS_STATUS);
//    }
//
//    public void authSuccessByToken(String action, String result) {
//        saveByToken(action,result,LOG_TYPE_AUTH,SUCCESS_STATUS);
//    }
//
//    public void authError(String admin, String action, String result) {
//        saveLog(admin,action,result,LOG_TYPE_AUTH,FAIL_STATUS);
//    }
//    //TODO 订单日志
//    public void orderSuccess(String action, String result) {
//        saveByToken(action,result,LOG_TYPE_ORDER,SUCCESS_STATUS);
//    }
//    public void orderError(String action, String result) {
//        saveByToken(action,result,LOG_TYPE_ORDER,FAIL_STATUS);
//    }
//    //TODO 其它日志
//
//    //保存日志到数据库
//    private void saveLog(String admin, String action, String result, Integer type, Integer status) {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        ShoppingLog log = new ShoppingLog();
//        log.setAdmin(admin);
//        log.setIp(IpUtil.getIpAddr(request));
//        log.setType(type+"");
//        log.setAction(action);
//        log.setStatus(status+"");
//        log.setResult(result);
//        logUtil.shoppingLogMapper.saveLog(log);
//    }
//
//    private void saveByToken(String action, String result, Integer type, Integer status) {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        //获取请求头里的token参数
//        String token = request.getHeader("Shopping-Admin-Token");
//        //通过token获取用户ID
//        Integer id = JwtHelper.getUserId(token);
//        ShoppingAdmin admin = logUtil.shoppingAdminMapper.selectById(id);
//        //查询用户信息
//        ShoppingLog log = new ShoppingLog();
//        log.setAdmin(admin.getUsername());
//        log.setIp(IpUtil.getIpAddr(request));
//        log.setType(type+"");
//        log.setAction(action);
//        log.setStatus(status+"");
//        log.setResult(result);
//        logUtil.shoppingLogMapper.saveLog(log);
//    }
//}
