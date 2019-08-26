package com.example.demo.service;

import com.example.demo.model.BActicles;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BActiclesService {

    /**
     *  分页查询文章
     * @param page 当前页
     * @param size 每页条数
     * @return
     */
    List<BActicles> findLimit(Integer page,Integer size);


    /**
     * 查询文章总数  计算分页
     * @return
     */
    Integer findAllCount();


    /**
     * 根据文章编号查看文章详情
     * @param acticle_id 文章id
     * @return  实体类数据
     */
    BActicles acticle_info(long acticle_id);
}
