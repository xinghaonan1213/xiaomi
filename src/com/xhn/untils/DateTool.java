package com.xhn.untils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTool {
	//设置格式
	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     //设置字符串转化成日期的方法
	public static Date stringToDate(String str) {
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//设置日期转换成字符串的方法
	public static String dateToString(Date date) {
		return sdf.format(date);
	}
}
