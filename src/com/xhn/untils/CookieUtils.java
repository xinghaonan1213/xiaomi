package com.xhn.untils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
	/*
 	����Cookie�ķ���
 	search����������������
 	request������Ϊ�˻�ȡCookie�е�����
 	response������Ϊ�˽����ɵ�Cookie��Ӧ�����������
 */

	public static void addCookie(String search,HttpServletRequest request,
			HttpServletResponse response) {
		/*
	 	�Ȼ�ȡ֮ǰ�洢������ʷCookie�е���Ϣ
	 	ƴ���ϵ�ǰ����������(�Ѵ��ڵĲ�ƴ��)
	 	��������֮����������ݴ���Cookie
	 */
       String info = getCookieInfo(request);
       //���ж��Ƿ��ǵ�һ�δ洢��cookie�е�����
       if ("".equals(info)) {
		 info=search;
	    }else {
	    	/*
		 	����ƴ��ʱ��Ҫ����������⣺
		 	1���ظ��������ؼ��ֲ���ӽ�Cookie
		 	2��Cookie��ֻ�洢���������ؼ���
		 */
          if (!info.contains(search)) {
			   //�ж��Ƿ����#
        	  boolean isContain = info.contains("#");
        	  if (isContain) {
			    //����#	
        		  //���ַ�������#��ȡ
        		  String[] strs = info.split("#");
        	   //������Ѱ���2��#���Ѿ������������ݣ���ô������ӽ���һ�����ݸ��ǵ�
        		  if (strs.length==3) {
					info=strs[1]+"#"+strs[2];
				}
			}
        	  info=info+"#"+search;
       	  }
		}
		//��ȡcookie
		Cookie cookie=new Cookie("search",info);
		response.addCookie(cookie);
	}
	
	//��ȡcookie�е�����
	public static String getCookieInfo(HttpServletRequest request) {
		String info = "";//����һ�����ַ���������Cookie�е�����
		Cookie[] cookies = request.getCookies();
		if (cookies!=null && cookies.length!=0  ) {
			//����
			for (Cookie cookie : cookies) {
				if ("search".equals(cookie.getName())) {
					info = cookie.getValue();
				}
			}
		}
		return info;
	}
}
