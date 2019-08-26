package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.JsonResponse.Response;
import com.example.demo.model.BActicles;
import com.example.demo.service.BActiclesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Acticle")
public class BActiclesController extends BaseController {

    //service 服务
    @Autowired
    private BActiclesService bActiclesService;




            /**
             * 查询文章数量
             * @return
             */
    @RequestMapping("/IndexActicleCount")
    public Object IndexActicleCount(){

        try {

            Integer ActicleCount = bActiclesService.findAllCount();


            if (ActicleCount != null) {

                Map<String,Integer> map = new HashMap<>();
                map.put("ActicleCount",ActicleCount);

                List<Object> data = new ArrayList<>();

                data.add(JSON.toJSONString(map));

                return Response.build(Response.ResponseCode.SUCCESS,data);

            }else{

                return Response.build(Response.ResponseCode.ERROR, "网络异常请重试");

            }

        }catch (Exception e) {

            return Response.build(Response.ResponseCode.ERROR, "网络异常请重试");

        }
    }


    /**
     * 文章分页数量
     * @param pageNum 当前页
     * @param pageSize 每页条数
     * @return
     */
    @RequestMapping("/index")
    public Object Index( @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                     int pageNum,
                         @RequestParam(name = "pageSize", required = false, defaultValue = "5")
                                     int pageSize) throws Exception {


            try{

                if(pageNum > (bActiclesService.findAllCount()-1)/pageSize+1){

                    return Response.build(Response.ResponseCode.ERROR,"请检查参数是否正确");
                }

                List<BActicles> bActiclesList = bActiclesService.findLimit(pageNum,pageSize);

                if(bActiclesList!=null){

                    return Response.build(Response.ResponseCode.SUCCESS,bActiclesList);

                }else {

                    return Response.build(Response.ResponseCode.ERROR,"请检查参数是否正确");

                }

            }catch (Exception e){

                return  Response.build(Response.ResponseCode.TRYS);

            }

    }


    /**
     * 根据文章id 查询详情
     * @param acticle_id 文章id
     * @return 返回文章详情
     */
    @RequestMapping("/acticle_info")
    public Object acticle_info(long acticle_id){

        try {

            if(acticle_id<0 || acticle_id==0){
                return Response.build(Response.ResponseCode.ERROR,"参数不能小于0");
            }

            BActicles acticle = bActiclesService.acticle_info(acticle_id);

            if(acticle==null){

                return Response.build(Response.ResponseCode.ERROR,"未查询到数据");

            }else {

                return Response.build(Response.ResponseCode.SUCCESS,acticle);

            }


        }catch (Exception e){

            return Response.build(Response.ResponseCode.TRYS);
        }


    }































}
