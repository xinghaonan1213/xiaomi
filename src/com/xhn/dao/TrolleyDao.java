package com.xhn.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.xhn.entity.Trolley;
import com.xhn.entity.User;

public class TrolleyDao {
    private QueryRunner qrRunner=new QueryRunner(new ComboPooledDataSource());

	public Trolley confirmIsRepeat(Trolley trolley) {
		Trolley t=null;
		try {
			t=qrRunner.query("select * from trolley where uid=? and gid=? and orders_number is null",
					new BeanHandler<Trolley>(Trolley.class), trolley.getUid(),trolley.getGid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}

	public void addTrolley(Trolley trolley) {
		//添加操作
		try {
			qrRunner.update("insert into trolley (uid,gid,number) values"
					+ "(?,?,?)", trolley.getUid(),trolley.getGid(),trolley.getNumber());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void updateTrolley(Trolley t) {
		//进行修改操作
		try {
			qrRunner.update("update trolley set number=? where tid=?",
					t.getNumber(),t.getTid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public int selectTrolleyCount(Integer uid) {
		int count=0;
		try {
			long c=(long)qrRunner.query("select count(*) from trolley where uid=? and orders_number is null",
					new ScalarHandler(), uid);
			count=(int)c;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public List<Trolley> findAllTrolley(User user) {
		//显示某个用户购物车中的数据
		List<Trolley> trolleys=null;
		try {
			trolleys=qrRunner.query("select * from trolley where uid=? and orders_number is null",
					new BeanListHandler<Trolley>(Trolley.class), user.getUid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return trolleys;
	}

	public int addOrDeleteNumber(String tid, String number) {
		int row=0;
		try {
			row=qrRunner.update("update trolley set number=? where tid=?",
					number,tid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}

	public int delGoods(String tid) {
		int row=0;
		try {
			row=qrRunner.update("delete from trolley where tid=?",tid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}

	public int updateTrolley(String orders_number, Integer uid) {
		int row=0;
		try {
			row=qrRunner.update("update trolley set orders_number=? where uid=? and orders_number is null",
					orders_number,uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}


}
