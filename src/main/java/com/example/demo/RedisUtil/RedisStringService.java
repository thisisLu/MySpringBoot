package com.example.demo.RedisUtil;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 配置redis 简单缓存类
 * 创建时间 2019-08-20 21：18
 * 创建人 小鹿
 * 原文链接 https://www.jianshu.com/p/fd65156ff630
 *
 * 参考链接 https://www.jianshu.com/p/b9154316227e
 */
@Service
public class RedisStringService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    //============================String=============================


    /**
     *  普通缓存
     * @param key string类型的唯一标示 不能为空
     * @param value 基本为实体类数据 转换成string类型
     * @param <T> 任意类型
     * @return true/false
     */
    public <T> boolean set(String key ,T value){

        try {

            //任意类型转换成String
            String val = beanToString(value);

            if(val==null||val.length()<=0){
                return false;
            }

            stringRedisTemplate.opsForValue().set(key,val);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    /**
     * 设置过期时间的普通缓存
     * @param key string类型的唯一标示 不能为空
     * @param value 基本为实体类数据 转换成string类型
     * @param time 过期时间 单位为秒
     * @param <T> 任意类型
     * @return
     */
    public <T> boolean set(String key ,T value,long time){

        try {

            //任意类型转换成String
            String val = beanToString(value);

            if(val==null||val.length()<=0){
                return false;
            }

            stringRedisTemplate.opsForValue().set(key,val,time,TimeUnit.SECONDS);
            return true;
        }catch (Exception e){
            return false;
        }

    }





    /**
     * 根据key返回任意类型的实体类
     * @param key 不可为null
     * @param clazz 任意类型
     * @param <T>   任意类型
     * @return 实体类数据
     */
    public <T> T get(String key,Class<T> clazz){
        try {
            String value = stringRedisTemplate.opsForValue().get(key);

            return stringToBean(value,clazz);
        }catch (Exception e){
            return null ;
        }
    }






    /**
     *
     * @param value
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    private <T> T stringToBean(String value, Class<T> clazz) {
        if(value==null||value.length()<=0||clazz==null){
            return null;
        }

        if(clazz ==int.class ||clazz==Integer.class){
            return (T)Integer.valueOf(value);
        }
        else if(clazz==long.class||clazz==Long.class){
            return (T)Long.valueOf(value);
        }
        else if(clazz==String.class){
            return (T)value;
        }else {
            return JSON.toJavaObject(JSON.parseObject(value),clazz);
        }
    }

    /**
     *
     * @param value 任意类型
     * @return String
     */
    private <T> String beanToString(T value) {

        if(value==null){
            return null;
        }
        Class <?> clazz = value.getClass();
        if(clazz==int.class||clazz==Integer.class){
            return ""+value;
        }
        else if(clazz==long.class||clazz==Long.class){
            return ""+value;
        }
        else if(clazz==String.class){
            return (String)value;
        }else {
            return JSON.toJSONString(value);
        }
    }




    /**
     * 指定缓存失效时间
     * @param key 键
     * @param time 时间(秒)
     * @return
     */
    public boolean expire(String key,long time){
        try {
            if(time>0){
                stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }




    /**
     * 判断key是否存在
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key){
        try {
            return stringRedisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    /**
     * 删除缓存
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public boolean del(String  key){
            try{
                stringRedisTemplate.delete(key);
                return true;
            }catch (Exception e){
                return false;
            }

    }



    // list map set hash 等暂时先不弄  如果弄建议转换成json格式进行存储





}
