package com.example.demo.dao;

import com.example.demo.model.BUsers;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {
    /**
     * 添加用户
     * @param bUsers
     * @return 返回1
     */
    @Insert("INSERT INTO b_users (username,password,jointime,ipsource,iptity) VALUE(#{username},#{password},#{jointime},#{iPsource},#{iptity}) ")
    int adduser(BUsers bUsers);

    /**
     * 用户登录
     * @param user
     * @return 实体类数据
     */
    @Select("SELECT uid,username,Jointime,IPsource,state,iptity FROM b_users where username=#{username} and password=#{password} ")
    BUsers User_login(BUsers user);


    /**
     * 查询用户列表
     * @return
     */
    @Select("select * from b_users")
    List<BUsers> selectUsers();

    /**
     * 用户注册时 查找 邮箱OrIP是否存在
     * @param name
     * @param ip
     * @return 存在返回当前ID
     */
    @Select("SELECT uid FROM b_users where username=#{name} OR IPsource=#{ip} ")
    Integer NameOrIPExists(@Param("name") String name, @Param("ip")String ip);

    /**
     * 测试redis 缓存
     * @return
     */
    @Select("select * from b_users where uid=#{uid}")
    BUsers testredis(@Param("uid") long uid);
}
