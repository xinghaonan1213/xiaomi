package com.xhn.untils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTool {
	//���ø�ʽ
	private static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     //�����ַ���ת�������ڵķ���
	public static Date stringToDate(String str) {
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//��������ת�����ַ����ķ���
	public static String dateToString(Date date) {
		return sdf.format(date);
	}
}
