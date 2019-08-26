package com.example.demo.controller;

import com.example.demo.JsonResponse.Response;
import com.example.demo.Util.DesUtils;
import com.example.demo.Util.RandomValidateCode;
import com.example.demo.model.BUsers;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间 2019-08-12 17：26
 * 创建人 小鹿
 * 个人项目
 * 用户类接口
 *
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

    //用户接口
    @Autowired
    private UserService userService;




    /**
     * 用户注册接口
     * @param user  参数包含 username password  不可为空
     * @param verify    验证码
     * @return 200 注册成功
     */
    @PostMapping("/add")
    public Object addUser(BUsers user, String verify){


        try{
            //认证 验证码
            RandomValidateCode R = new RandomValidateCode();
            if(!verify.equalsIgnoreCase(session.getAttribute(R.RANDOMCODEKEY).toString())){


                return Response.build(Response.ResponseCode.ERROR,"验证码错误");//验证码错误
            }
            //通过 Name OR IP 查找 如果存在返回当前id  否则返回null
            System.out.println(userService.NameOrIPExists(user.getUsername(),getIpAddr(request)));
            if(userService.NameOrIPExists(user.getUsername(),getIpAddr(request))!=null){


                return Response.build(Response.ResponseCode.ERROR,"用户名或IP已经存在");

            }else{


                //加密密码 变成密文存进数据库
                //注意这里，自定义的加密的KEY要和解密的KEY一致，这就是钥匙，如果你上锁了，却忘了钥匙，那么是解密不了的
                DesUtils des = new DesUtils("leemenz"); //自定义密钥
                user.setPassword(des.encrypt(user.getPassword()));//加密



                //如果满足上面的条件会进行插入
                user.setIPsource(getIpAddr(request));//获取客户端IP
                user.setIptity(getIpDescr(getIpAddr(request)));
                user.setJointime(GetSqlTime());//获取当前sql时间函数

                if(userService.adduser(user)>0){//成功插入返回1

                    return  Response.build(Response.ResponseCode.SUCCESS,"注册成功");

                }else{

                    return  Response.build(Response.ResponseCode.ERROR,"注册失败");
                }


            }

        }catch (Exception e){



           return Response.build(Response.ResponseCode.ERROR,"网络异常请重试");
        }

    }


    /**
     * 用户登录
     * @param user 实体类 包含 账号 密码
     * @param verify 验证码
     * @return  成功返回ok
     */
    @PostMapping("/Log")
    public Object Log(BUsers user,String verify){

        try{

            RandomValidateCode R = new RandomValidateCode();
            if(!verify.equalsIgnoreCase(session.getAttribute(R.RANDOMCODEKEY).toString())){

                return Response.build(Response.ResponseCode.ERROR,"验证码错误");//验证码错误
            }
            DesUtils des = new DesUtils("leemenz"); //自定义密钥
            user.setPassword(des.encrypt(user.getPassword()));//进行加密对比

            BUsers bUsers = userService.User_login(user);
            //用户名和密码正确 返回实体类数据
            if(bUsers!=null){

                //把用户名称存进Session，方便前端使用
                session.setAttribute("username",user.getUsername());

                List<BUsers> bUsersList = new ArrayList<>();

                bUsersList.add(bUsers);

                return Response.build(Response.ResponseCode.SUCCESS,"登录成功",bUsersList);//用户名正确
            }
            else{

                return Response.build(Response.ResponseCode.ERROR,"用户名或密码错误");//用户名错误
            }
        }catch (Exception e){
            return Response.build(Response.ResponseCode.ERROR,"遇到未知错误,请重试");//"遇到未知错误,请重试."
        }
    }








    @GetMapping("/all")
    public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5")
                    int pageSize){

        return userService.findAllUser(pageNum,pageSize);
    }


    /**
     *  输出验证码
     * @param request   客户端响应头目标源
     * @param response
     */
    @RequestMapping("checkCode")
    public void checkCode(HttpServletRequest  request, HttpServletResponse response) {
        //设置相应类型,告诉浏览器输出的内容为图片
        response.setContentType("image/jpeg");

        //设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);

        RandomValidateCode randomValidateCode = new RandomValidateCode();

        try {
            randomValidateCode.getRandcode(request, response);//输出图片方法
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }




}
