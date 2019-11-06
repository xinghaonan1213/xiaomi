package com.xhn.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.xhn.dao.UserDao;
import com.xhn.entity.User;
import com.xhn.untils.PageTool;
import com.xhn.untils.SendMessage;

public class UserService {
    private  UserDao userDao=new UserDao();
	public boolean checkRegiste(String phone) {
	  User user=userDao.checkRegiste(phone);
	  if (user!=null) {
		return true;
	}
		return false;
	}
	public boolean checkUsername(String username) {
	  //将数据传到dao层进行数据的查询
		User user=userDao.checkUsername(username);
		if (user!=null) {
			return true;
		}
		return false;
	}
	public boolean registeUser(User user) {
		int row=userDao.registeUser(user);
		if (row>0) {
			return true;
		}
		return false;
	}
	public boolean createCode(String phone, HttpSession session) {
	    //调用工具类来生成验证码
		int code=SendMessage.createCode(phone);
		String newCode=phone+"#"+code;
		//判断验证码
		if ( code==0 ) {
			return false;
		}else {
			//将验证码和手机号放到session中记录是否匹配
			session.setAttribute("code", newCode);
			return true;
		}
	}
	//校验登陆信息
	public boolean validateCode(String phone, String code,HttpSession session) {
	    String newCode=phone+"#"+code;
	    //获取之前存储在session中的phone和code值
	     String oldCode =(String )session.getAttribute("code");
	    /* if (newCode.equals(oldCode)) {
			return true;
		}*/
	     //通过唯一值phone来获取当前用户信息user，并存储在session对象中
	    User user=userDao.selectUserByPhone(phone);
	    session.setAttribute("user", user);
		return true;
	}
	public boolean adminLogin(User user, HttpSession session) {
		User u=userDao.adminLogin(user);
		if (u!=null) {
			session.setAttribute("user", u);
			return true;
		}
		return false;
	}
	public List<User> findAllUser(PageTool pageTool) {
		return userDao.findAllUser(pageTool);
	}
	public int selectUserCount() {
		return userDao.selectUserCount();
	}
	public boolean updateManager(User user) {
		int row=userDao.updateManager(user);
		if (row>0) {
			return true;
		}
		return false;
	}
	public boolean deleteById(String ids) {
		int row=userDao.deleteById(ids);
		if (row>0) {
			return true;
		}
		return false;
	}



}
