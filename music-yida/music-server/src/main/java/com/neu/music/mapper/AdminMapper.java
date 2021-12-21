package com.neu.music.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @description: 管理员
 * @Author: yida
 */
@Repository
public interface AdminMapper {
    /*验证密码是否正确*/
    int verifyPassword(@Param("username") String username, @Param("password") String password);
}
