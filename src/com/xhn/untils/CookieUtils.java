package com.xhn.untils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
	/*
 	生成Cookie的方法
 	search参数：搜索的内容
 	request参数：为了获取Cookie中的内容
 	response参数：为了将生成的Cookie响应给浏览器保存
 */

	public static void addCookie(String search,HttpServletRequest request,
			HttpServletResponse response) {
		/*
	 	先获取之前存储搜索历史Cookie中的信息
	 	拼接上当前的搜索内容(已存在的不拼接)
	 	将处理完之后的搜索内容存入Cookie
	 */
       String info = getCookieInfo(request);
       //先判断是否是第一次存储到cookie中的数据
       if ("".equals(info)) {
		 info=search;
	    }else {
	    	/*
		 	在做拼接时，要解决两个问题：
		 	1、重复的搜索关键字不添加进Cookie
		 	2、Cookie中只存储三个搜索关键字
		 */
          if (!info.contains(search)) {
			   //判断是否包含#
        	  boolean isContain = info.contains("#");
        	  if (isContain) {
			    //包含#	
        		  //将字符串按照#截取
        		  String[] strs = info.split("#");
        	   //如果是已包含2个#即已经包含三条数据，那么继续添加将第一条数据覆盖掉
        		  if (strs.length==3) {
					info=strs[1]+"#"+strs[2];
				}
			}
        	  info=info+"#"+search;
       	  }
		}
		//获取cookie
		Cookie cookie=new Cookie("search",info);
		response.addCookie(cookie);
	}
	
	//获取cookie中的数据
	public static String getCookieInfo(HttpServletRequest request) {
		String info = "";//定义一个空字符串来保存Cookie中的内容
		Cookie[] cookies = request.getCookies();
		if (cookies!=null && cookies.length!=0  ) {
			//遍历
			for (Cookie cookie : cookies) {
				if ("search".equals(cookie.getName())) {
					info = cookie.getValue();
				}
			}
		}
		return info;
	}
}
