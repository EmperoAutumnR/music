package com.neu.music.mapper;

import com.neu.music.entity.Consumer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description: 用户
 * @Author: yida
 */
@Repository
public interface ConsumerMapper {
    /**
     *增加
     */
    int insert(Consumer consumer);

    /**
     *修改
     */
    int update(Consumer consumer);

    /**
     * 删除
     */
    int delete(Integer id);

    /**
     * 根据主键查询整个对象
     */
    Consumer selectByPrimaryKey(Integer id);

    /**
     * 查询所有用户
     */
    List<Consumer> allConsumer();

    /**
     * 验证密码
     */
    int verifyPassword(String username,String password);

    /**
     * 根据账号查询
     */
    Consumer getByUsername(String username);
}
















