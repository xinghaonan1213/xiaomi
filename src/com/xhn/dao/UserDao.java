package com.xhn.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.xhn.entity.User;
import com.xhn.untils.PageTool;

public class UserDao {
  private QueryRunner queryRunner=new QueryRunner(new ComboPooledDataSource());
	public User checkRegiste(String phone) {
		User user=null;
		try {
			user=queryRunner.query("select * from user where phone=?",
				 new BeanHandler<User>(User.class),phone);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	public User checkUsername(String username) {
		//操作数据库进行查询数据
		User user=null;
		try {
			user=queryRunner.query("select * from user where username=?", 
					new BeanHandler<User>(User.class),username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	public int registeUser(User user) {
		int row=0;
		try {
			row=queryRunner.update("insert into user(uname,gender,phone,area,username,password,photo,create_time)"
					+ "values(?,?,?,?,?,?,?,?)",user.getUname(),user.getGender(),user.getPhone(),user.getArea(),
					user.getUsername(),user.getPassword(),user.getPhoto(),user.getCreate_time());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	public User adminLogin(User user) {
	 //通过查询返回对应的信息
		User u=null;
		try {
			u=queryRunner.query("select * from user where username=? and password=? and manager=0",new BeanHandler<User>(User.class), 
					user.getUsername(),user.getPassword());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	public List<User> findAllUser(PageTool pageTool) {
		List<User> users=null;
	   try {
		users=queryRunner.query("select * from user limit ?,?", new BeanListHandler<User>(User.class),
				pageTool.getStartIndex(),pageTool.getPageCount());
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return users;
	}
	public int selectUserCount() {
	   int count=0;
	   try {
		long c=(long)queryRunner.query("select count(*) from user", new ScalarHandler());
		count=(int)c;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return count;
	}
	public int updateManager(User user) {
		int row=0;
		try {
			row=queryRunner.update("update  user set manager=? where uid=?",
					user.getManager(),user.getUid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	public int deleteById(String ids) {
		int row=0;
		try {
			row=queryRunner.update("delete from user where uid in("+ids+")");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	public User selectUserByPhone(String phone) {
		User user=null;
		try {
			user=queryRunner.query("select * from user where phone=?",
					new BeanHandler<User>(User.class), phone);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

}
