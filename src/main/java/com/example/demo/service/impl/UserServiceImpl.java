package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.model.BUsers;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public int adduser(BUsers user) {
        return userDao.adduser(user);
    }

    /*
     * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
     * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
     * pageNum 开始页数
     * pageSize 每页显示的数据条数
     * */
    @Override
    public PageInfo<BUsers> findAllUser(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        List<BUsers> userDomains = userDao.selectUsers();
        PageInfo result = new PageInfo(userDomains);
        return result;
    }



    @Override
    public BUsers testredis(long uid) {
        return userDao.testredis(uid);
    }


    /**
     * 测试
     * @return
     */
    @Override
    public List<BUsers> selectUsers() {
        return userDao.selectUsers();
    }

    /**
     * 用户登录
     * @param user
     * @return 返回用户列表
     */
    public BUsers User_login(BUsers user){

        return userDao.User_login(user);
    }

    /**
     * 用户注册时 通过 name OR Ip 查找 是否存在
     * @param name
     * @param ip
     * @return  存在返回当前ID
     */
    @Override
    public Integer NameOrIPExists(String name, String ip) {

        try{

            return userDao.NameOrIPExists(name, ip);

        }catch (Exception ex){

            return 0;
        }

    }




}
