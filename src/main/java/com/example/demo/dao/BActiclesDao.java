package com.example.demo.dao;

import com.example.demo.Util.UtilDao;
import com.example.demo.model.BActicles;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * 文章类
 */
public interface BActiclesDao {


    /**
     * Mybatis 的动态sql
     * @return
     */
    @SelectProvider(type = UtilDao.class,method = "changeSQL")
    List<BActicles> findLimit(String sql);

    /**
     * 查询文章总数  计算分页
     * @return
     */
    @Select("SELECT COUNT(*) FROM b_acticles where status='公开' ")
    Integer findAllCount();


    @Select("select * from b_acticles  where status='公开' and cid=#{acticle_id} ")
    BActicles acticle_info(@Param("acticle_id") long acticle_id);

}
