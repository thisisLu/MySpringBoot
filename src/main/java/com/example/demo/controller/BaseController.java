package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.ModelAttribute;


public class BaseController {

	/**
	 * 定义 request  response session可直接继承使用
	 */
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	
	@ModelAttribute
	private void init(HttpServletRequest request,HttpServletResponse response) {
		this.request=request;
		this.response=response;
		this.session=request.getSession();
	}


	
	/**
	 * 获取项目的物理路径
	 * @return 路径
	 */
	protected String getRealPath() {
		return session.getServletContext().getRealPath("/");
	}


    /**
     * 返回当前时间  类型为sql.Timestamp 可直接插入数据库
     * @return
     */
    protected Timestamp GetSqlTime(){

	    return new java.sql.Timestamp(System.currentTimeMillis());
    }


	/**
	 * 加密
	 * @return
	 */
	/*protected String MD5(String str){
		Mademd5 md5 = new Mademd5();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHH");//设置日期格式
		df.format(new Date());// new Date()为获取当前系统时间
		String current = df.format(System.currentTimeMillis());

		String text = str+current;
		
		String key=md5.toMd5(text);
		
		return key;
	} */

	
	
	/** 
     * 获取当前网络ip 
     * @param request 
     * @return 客服端IP
	 */
	protected String getIpAddr(HttpServletRequest request){
        String ipAddress = request.getHeader("x-forwarded-for");  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getHeader("Proxy-Client-IP");  
            }  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getHeader("WL-Proxy-Client-IP");  
            }  
            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
                ipAddress = request.getRemoteAddr();  
                if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){  
                    //根据网卡取本机配置的IP  
                    InetAddress inet=null;  
                    try {  
                        inet = InetAddress.getLocalHost();  
                    } catch (UnknownHostException e) {  
                        return "0.0.0.0" ;
                    }  
                    ipAddress= inet.getHostAddress();  
                }  
            }  
            //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
            if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15  
                if(ipAddress.indexOf(",")>0){  
                    ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));  
                }  
            }  
            return ipAddress;   
    }
	
	/**
	 * 获取当前时间
	 * @return
	 */
	protected Object GetDayTime(){
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		//df.format(new Date());// new Date()为获取当前系统时间
		
		return  df.format(new Date());
	}
	
	/**
	 * 根据IP地址返回地址描述
	 * @param strIP
	 * @return
	 */
	protected String getIpDescr(String strIP){
        try
          {
            URL url = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=" + strIP);
            URLConnection conn = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line = null; 
            StringBuffer result = new StringBuffer(); 
            while((line = reader.readLine()) != null)
            { 
              result.append(line); 
            } 
            reader.close();    
            String jsonResult=result.toString();

			  JSONObject jsonObject = JSONObject.parseObject(jsonResult);

            JSONObject jsonObject1=(JSONObject)jsonObject.get("data");
            String country = jsonObject1.get("country").toString();
            String area =jsonObject1.get("area").toString();
            String region =jsonObject1.get("region").toString();
            String city =jsonObject1.get("city").toString();
            String isp =jsonObject1.get("isp").toString();
            StringBuffer descr = new StringBuffer();
            descr.append(country).append(area).append(":").append(region).append(":").append(city).append(":").append(isp);
            return descr.toString();
          }
         catch( IOException e)
          {
            return "网络错误.未知";
          }

    }
	
	
	
}
