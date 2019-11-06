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
	  //�����ݴ���dao��������ݵĲ�ѯ
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
	    //���ù�������������֤��
		int code=SendMessage.createCode(phone);
		String newCode=phone+"#"+code;
		//�ж���֤��
		if ( code==0 ) {
			return false;
		}else {
			//����֤����ֻ��ŷŵ�session�м�¼�Ƿ�ƥ��
			session.setAttribute("code", newCode);
			return true;
		}
	}
	//У���½��Ϣ
	public boolean validateCode(String phone, String code,HttpSession session) {
	    String newCode=phone+"#"+code;
	    //��ȡ֮ǰ�洢��session�е�phone��codeֵ
	     String oldCode =(String )session.getAttribute("code");
	    /* if (newCode.equals(oldCode)) {
			return true;
		}*/
	     //ͨ��Ψһֵphone����ȡ��ǰ�û���Ϣuser�����洢��session������
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
