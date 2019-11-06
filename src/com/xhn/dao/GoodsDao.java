package com.xhn.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.xhn.entity.Goods;
import com.xhn.untils.PageTool;

public class GoodsDao {
   private QueryRunner queryRunner=new QueryRunner(new ComboPooledDataSource());

public int insertGoods(Goods goods) {
	int row=0;
	try {
		row=queryRunner.update("insert into goods values(null,?,?,?,?,?,?,?,?,?,?,?)",
				goods.getCid(),goods.getGname(),goods.getColor(),goods.getSize(),
				goods.getPrice(),goods.getDescription(),goods.getFull_description(),
				goods.getPic(),goods.getState(),goods.getVersion(),goods.getProduct_date());
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return row;
  }

public int selectGoodsCount() {
	int count=0;
	try {
	long c=(long)queryRunner.query("select count(*) from goods", new ScalarHandler());
	count=(int)c;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return count;
}

public List<Goods> findAllGoods(PageTool pageTool) {
	List<Goods> goods=null;
	try {
		goods=queryRunner.query("select * from goods limit ?,?", new BeanListHandler<Goods>(Goods.class),
				pageTool.getStartIndex(),pageTool.getPageCount());
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return goods;
}

public Goods findGoodsById(String gid) {
    Goods goods=null;
    try {
		goods=queryRunner.query("select * from goods where gid=?",new BeanHandler<Goods>(Goods.class), gid);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return goods;
}

public int updateGoods(Goods goods) {
	int row=0;
	try {
		row=queryRunner.update("update goods set cid=?,gname=?,color=?,size=?,price=?,description=?,full_description=?,pic=?,state=?,version=?,product_date=? where gid=?",
				goods.getCid(),goods.getGname(),goods.getColor(),goods.getSize(),
				goods.getPrice(),goods.getDescription(),goods.getFull_description(),
				goods.getPic(),goods.getState(),goods.getVersion(),goods.getProduct_date(),
				goods.getGid());
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return row;
}

public List<Goods> findGoodsByState(int state, int count) {
	List<Goods> goodes=null;
	try {
		goodes=queryRunner.query("select * from goods where state=? order by gid desc limit ?",
				new BeanListHandler<Goods>(Goods.class), state,count);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return goodes;
}

public List<Goods> finGoodsByCate(int cid, int count) {
	List<Goods> goodes=null;
	try {
	  goodes= queryRunner.query("select * from goods where cid=? order by gid desc limit ?", 
				new BeanListHandler<Goods>(Goods.class),cid,count);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return goodes;
  }

public int deleteById(String ids) {
	int row=0;
	try {
		row=queryRunner.update("delete from goods where cid in("+ids+")");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return row;
}

public List<Goods> finGoodsBySearch(String info, int count) {
	List<Goods> goodes = null;
	StringBuilder sb = new StringBuilder("select * from goods where ");
	//先判断info中是否包含#
	if (!info.contains("#")) {
		sb.append("gname like '%" + info + "%' ");
	} else {
		//按照# 分割info
		String[] strs = info.split("#");
		for (int i = 0; i < strs.length; i++) {
			if (i == 0) {
				//拼接第一个关键字，不带or
				sb.append("gname like '%" + strs[i] + "%' ");
			} else {
				//拼接除第一个之外的关键字，带or连接
				sb.append("or gname like '%" + strs[i] + "%' ");
			}
		}
	}
	//再把分页拼接进去
	sb.append("limit " + count);
	try {
	  goodes=queryRunner.query(sb.toString(), new BeanListHandler<Goods>(Goods.class));
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return goodes;
  }
}