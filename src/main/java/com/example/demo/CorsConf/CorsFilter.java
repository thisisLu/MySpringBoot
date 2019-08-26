package com.example.demo.CorsConf;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 全局配置Cors WebMvcConfigurer 跨域
 * 创建时间 2019-08-24 19:58
 * 修改人 小鹿
 *
 * 修改时间 2019-08-25 12:36
 *
 * Springboot 版本4 以后不建议使用 继承 WebMvcConfigurer 来配置
 * 所以改用 实现 父类 Filter 接口
 * ok 完成
 *
 */
@Component
public class CorsFilter implements Filter  {

    final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CorsFilter.class);


    /**
     * 开发和测试环境
     * @param req
     * @param res
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;

        HttpServletRequest reqs = (HttpServletRequest) req;
        StringBuffer url = reqs.getRequestURL();
        String tempContextUrl = url.delete(url.length() - reqs.getRequestURI().length(), url.length()).append("/").toString();

        logger.info(reqs.getHeader("Origin"));
        logger.info(url.toString());
        logger.info(tempContextUrl);



            //放行那些ip的请求 * 为全部放行，不建议使用
            response.setHeader("Access-Control-Allow-Origin","*");//开发环境

            //表示是否 允许携带 Cookie Seesion  信息
            response.setHeader("Access-Control-Allow-Credentials", "true");

            // 放行那些 请求类型
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");

            // 表示 最长使用时间  Ps:这个好像没啥用，如果到了3600时间 浏览器数据会不会消失？
            response.setHeader("Access-Control-Max-Age", "3600");

            // 表示 返回给浏览器的头部信息
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with");

            chain.doFilter(req, res);
    }


    /**
     * 生产环境
     * @param
     * @param
     * @param
     * @throws IOException
     * @throws ServletException
     */
    /*public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;

        HttpServletRequest reqs = (HttpServletRequest) req;

        *//*StringBuffer url = reqs.getRequestURL();
        String tempContextUrl = url.delete(url.length() - reqs.getRequestURI().length(), url.length()).append("/").toString();*//*
        logger.info(reqs.getHeader("Origin"));
        //生产环境
        if("http://103.98.112.178".equals(reqs.getHeader("Origin"))){

            //放行指定IP或域名，可以为多个参数
            response.setHeader("Access-Control-Allow-Origin","http://103.98.112.178");//生产环境

            //表示是否 允许携带 Cookie Seesion  信息
            response.setHeader("Access-Control-Allow-Credentials", "true");

            // 放行那些 请求类型
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");

            // 表示 最长使用时间  Ps:这个好像没啥用，如果到了3600时间 浏览器数据会不会消失？
            response.setHeader("Access-Control-Max-Age", "3600");

            // 表示 返回给浏览器的头部信息
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with");

            chain.doFilter(req, res);
            //生产环境
        }else{
            interceptor();
        }
    }

    public Object interceptor(){
        return Response.build(Response.ResponseCode.ERROR,"请求域错误");
    }*/

    public void init(FilterConfig filterConfig) {}
    public void destroy() {}
}
