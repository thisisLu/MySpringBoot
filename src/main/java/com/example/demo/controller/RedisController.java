package com.example.demo.controller;

import com.example.demo.JsonResponse.Response;
import com.example.demo.model.BActicles;
import com.example.demo.model.BUsers;
import com.example.demo.RedisUtil.RedisStringService;
import com.example.demo.service.BActiclesService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/redis")
public class RedisController {

    /**
     * String 类型的redis 缓存工具类
     */
    @Autowired
    private RedisStringService redisStringService;



    /**
     * 用户 接口层
     */
    @Autowired
    private UserService UserService;

    /**
     * 文章 接口层
     */
    @Autowired
    private BActiclesService bActiclesService;



    @RequestMapping("/redisSet")
    public Object Redistest(long acticle_id){

        try {

            if(acticle_id<0 || acticle_id==0){
                return Response.build(Response.ResponseCode.ERROR,"参数不能小于0");
            }

            BActicles bUsers1 = redisStringService.get("acticle"+acticle_id,BActicles.class);



            if(bUsers1==null){
                BActicles acticle = bActiclesService.acticle_info(acticle_id);

                redisStringService.set("acticle"+acticle_id,acticle,60*10);

                if(acticle==null){
                    return Response.build(Response.ResponseCode.SUCCESS,"未查询到数据");
                }

                return Response.build(Response.ResponseCode.SUCCESS,"来自数据库",acticle);

            }else{

                return Response.build(Response.ResponseCode.SUCCESS,"来自缓存",bUsers1);
            }



        }catch (Exception e){

            return Response.build(Response.ResponseCode.TRYS);
        }



    }






    }






