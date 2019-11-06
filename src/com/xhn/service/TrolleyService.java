package com.xhn.service;


import java.util.List;

import com.xhn.dao.TrolleyDao;
import com.xhn.entity.Goods;
import com.xhn.entity.Trolley;
import com.xhn.entity.User;

public class TrolleyService {
   private TrolleyDao trolleyDao=new TrolleyDao();
   private GoodsService goodsService=new GoodsService();
public boolean addTrolley(Trolley trolley) {
	/*
 	现在参数trolley中只有uid、gid有值
 	主键tid自增长无需考虑
 	订单编号orders_number 默认为null也无需考虑
 	但是商品数量number需要在此赋值
 */
/*
 	当前添加的商品对应的购物车对象是否存在，是我们需要考虑的问题
 	如果存在，我们只需要累加number商品数量即可	sql执行修改update操作	返回true
 	如果不存在，将number赋值为1	sql执行insert插入操作	返回false
 */

	//先查询数据库是否有此商品的加购记录
	Trolley t=trolleyDao.confirmIsRepeat(trolley);
	if (t==null) {
		//为空，那么是第一次添加，需要添加，给number赋值为1
		trolley.setNumber(1);
		trolleyDao.addTrolley(trolley);
		return false;
	}else {
		//不为空，那么是已经存在该商品需要修改后台的number值，
		//在已经存在的数据上给number+1
		t.setNumber(t.getNumber()+1);
		trolleyDao.updateTrolley(t);
		return true;
	}
	
  }

public int selectTrolleyCount(Integer uid) {
	return trolleyDao.selectTrolleyCount(uid);
  }

public List<Trolley> findAllTrolley(User user) {
    //应为查出的数据需要显示user的数据和goods的数据，所以，
	//遍历trolley并查询出对应的goods的数据，并将它们添加到trolley中
	List<Trolley> trolleys=trolleyDao.findAllTrolley(user);
    for (Trolley trolley : trolleys) {
		trolley.setUser(user);
		Goods goods = goodsService.findGoodsById(String.valueOf(trolley.getGid()));
		trolley.setGoods(goods);
	}
	return trolleys;
}

public boolean addOrDeleteNumber(String tid, String number) {
	int row=trolleyDao.addOrDeleteNumber(tid,number);
	if (row>0) {
		return true;
	}
	return false;
}

public boolean delGoods(String tid) {
	int row=trolleyDao.delGoods(tid);
	if (row>0) {
		return true;
	}
	return false;
}

public boolean updateTrolley(String orders_number, Integer uid) {
	int row=trolleyDao.updateTrolley(orders_number,uid);
	if (row>0) {
		return true;
	}
	return false;
}
}
