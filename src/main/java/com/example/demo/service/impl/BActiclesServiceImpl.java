package com.example.demo.service.impl;

import com.example.demo.dao.BActiclesDao;
import com.example.demo.model.BActicles;
import com.example.demo.service.BActiclesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BActiclesServiceImpl implements BActiclesService {

    @Autowired
    private BActiclesDao bActiclesDao;

    /**
     *  分页查询文章
     * @param page 当前页
     * @param size 每页条数
     * @return
     */
    @Override
    public List<BActicles> findLimit(Integer page, Integer size) {
        StringBuffer sql = new StringBuffer();

       sql.append("SELECT * FROM b_acticles where status='公开' ORDER BY releasetime DESC ");
       if(page==null || size==null){
           page = 0;
           size = 5;
       }else if(page>0){
           page =  (page-1)*size;

       }

       sql.append("LIMIT "+page+","+size+" ");


       return bActiclesDao.findLimit(sql.toString());

    }


    /**
     * 查询文章总数  计算分页
     * @return
     */
    @Override
    public Integer findAllCount() {
        return bActiclesDao.findAllCount();
    }


    /**
     * 根据文章编号查看文章详情
     * @param acticle_id 文章id
     * @return  实体类数据
     */
    @Override
    public BActicles acticle_info(long acticle_id) {
        return bActiclesDao.acticle_info(acticle_id);
    }


}
