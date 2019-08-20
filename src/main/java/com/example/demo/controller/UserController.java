package com.example.demo.controller;

import com.example.demo.Util.DesUtils;
import com.example.demo.Util.RandomValidateCode;
import com.example.demo.model.BUsers;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

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
     * @param user  包含实体类 Name password...
     * @param verify    验证码
     * @return 成功插入返回 1 ，
     */
    @ResponseBody
    @PostMapping("/add")
    public int addUser(BUsers user, String verify){



        try{
            //认证 验证码
            RandomValidateCode R = new RandomValidateCode();
            if(!verify.equalsIgnoreCase(session.getAttribute(R.RANDOMCODEKEY).toString())){
                return -1;//验证码错误
            }
            //通过 Name OR IP 查找是否 存在当前用户名或IP地址
            if(userService.Name_Ip(user.getUsername(),getIpAddr(request))!=null){
                return 4;
            }

            //加密密码 变成密文存进数据库
            try {

                //注意这里，自定义的加密的KEY要和解密的KEY一致，这就是钥匙，如果你上锁了，却忘了钥匙，那么是解密不了的
                DesUtils des = new DesUtils("leemenz"); //自定义密钥
                user.setPassword(des.encrypt(user.getPassword()));//加密

            } catch (Exception e) {
                return 0;
            }

                //如果满足上面的条件会进行插入
                user.setIPsource(getIpAddr(request));//获取客户端IP
                user.setJointime(new Date(0));//获取当前时间函数
                return userService.adduser(user); //成功插入返回1

        }catch (Exception e){
           return -2;
        }

    }


    /**
     * 用户登录
     * @param user 实体类 包含 账号 密码
     * @param verify 验证码
     * @return  成功返回ok
     */
    @RequestMapping("/Log")
    @ResponseBody
    public Integer Href(BUsers user,String verify){

        try{

            RandomValidateCode R = new RandomValidateCode();
            if(!verify.equalsIgnoreCase(session.getAttribute(R.RANDOMCODEKEY).toString())){

                return -1;//验证码错误
            }
            DesUtils des = new DesUtils("leemenz"); //自定义密钥
            user.setPassword(des.encrypt(user.getPassword()));//进行解密
            if(userService.User_id(user)!=null){
                //把用户名称存进Session，方便前端使用
                session.setAttribute("u_name",user.getUsername());

                return 1;//用户名正确
            }
            else{

                return 0;//用户名错误
            }
        }catch (Exception e){
            return -2;//"遇到未知错误,请重试."
        }
    }







    @ResponseBody
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
     * @param response  将验证码通过
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
