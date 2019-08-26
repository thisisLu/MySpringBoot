package com.example.demo.service;

import com.example.demo.model.BUsers;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {

    /**
     * 用户注册
     * @param user
     * @return
     */
    int adduser(BUsers user);

    /**
     * 用户登录
     * @param user 通过用户名OR 密码 查找
     * @return  用户列表
     */
    BUsers User_login(BUsers user);

    /**
     * 用户注册  通过账号 OR IP 查找 是否存在
     * @param name
     * @param ip
     * @return  有一个条件满足 就返回当前ID
     */
    Integer NameOrIPExists(String name,String ip);

    /**
     * 分页插件
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<BUsers> findAllUser(int pageNum, int pageSize);


    /**
     * 测试缓存
     * @return
     */
    BUsers testredis(long uid);



    List<BUsers> selectUsers();
}
