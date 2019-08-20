package com.example.demo.service;

import com.example.demo.model.BUsers;
import com.github.pagehelper.PageInfo;

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
     * @return  返回 当前ID 如果不存在 返回null
     */
    Integer User_id(BUsers user);

    /**
     * 用户注册  通过账号 OR IP 查找 是否存在
     * @param name
     * @param ip
     * @return  有一个条件满足 就返回当前ID
     */
    Integer Name_Ip(String name,String ip);

    /**
     * 分页插件
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<BUsers> findAllUser(int pageNum, int pageSize);
}
