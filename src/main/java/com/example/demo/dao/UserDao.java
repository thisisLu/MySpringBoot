package com.example.demo.dao;

import com.example.demo.model.BUsers;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    /**
     * 添加用户
     * @param record
     * @return 返回1
     */
    int insert(BUsers record);

    /**
     * 用户登录
     * @param user
     * @return
     */
    Integer User_id(BUsers user);


    /**
     * 查询用户列表
     * @return
     */
    List<BUsers> selectUsers();

    /**
     * 用户注册时 查找 邮箱OrIP是否存在
     * @param name
     * @param Ip
     * @return 存在返回当前ID
     */
    Integer Name_Ip(@Param("name") String name, @Param("ip")String ip);
}
