package com.xhn.untils;


import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class UploadUtils {
	public static String upload(String errorPath,Part part, HttpServletRequest request, HttpServletResponse response) {
		String photo = "";
		//获取文件名
		photo = part.getSubmittedFileName();
		photo = UUID.randomUUID() + photo;//解决了同名图片的存储问题
		//类型判断，前提要拿到文件类型
		String type = photo.substring(photo.lastIndexOf(".") + 1);
		if (!("jpeg".equalsIgnoreCase(type) || "jpg".equalsIgnoreCase(type) || "png".equalsIgnoreCase(type))) {
			request.setAttribute("msg", "上传的文件必须是图片类型！！！");
			try {
				request.getRequestDispatcher(errorPath).forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			photo = "";
		}
		
		//声明本地存储上传文件的目录路径
		String realPath = "E:/upload";//解决了文件丢失的问题
		//判断文件夹是否存在，选择执行创建文件夹
		File file = new File(realPath);
		if (!file.exists()) {
			file.mkdirs();
		}
		//将part中的内容写入文件
		try {
			part.write(realPath + "/" + photo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return photo;
	}
}

