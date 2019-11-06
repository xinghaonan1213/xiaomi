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
 	���ڲ���trolley��ֻ��uid��gid��ֵ
 	����tid���������迼��
 	�������orders_number Ĭ��ΪnullҲ���迼��
 	������Ʒ����number��Ҫ�ڴ˸�ֵ
 */
/*
 	��ǰ��ӵ���Ʒ��Ӧ�Ĺ��ﳵ�����Ƿ���ڣ���������Ҫ���ǵ�����
 	������ڣ�����ֻ��Ҫ�ۼ�number��Ʒ��������	sqlִ���޸�update����	����true
 	��������ڣ���number��ֵΪ1	sqlִ��insert�������	����false
 */

	//�Ȳ�ѯ���ݿ��Ƿ��д���Ʒ�ļӹ���¼
	Trolley t=trolleyDao.confirmIsRepeat(trolley);
	if (t==null) {
		//Ϊ�գ���ô�ǵ�һ����ӣ���Ҫ��ӣ���number��ֵΪ1
		trolley.setNumber(1);
		trolleyDao.addTrolley(trolley);
		return false;
	}else {
		//��Ϊ�գ���ô���Ѿ����ڸ���Ʒ��Ҫ�޸ĺ�̨��numberֵ��
		//���Ѿ����ڵ������ϸ�number+1
		t.setNumber(t.getNumber()+1);
		trolleyDao.updateTrolley(t);
		return true;
	}
	
  }

public int selectTrolleyCount(Integer uid) {
	return trolleyDao.selectTrolleyCount(uid);
  }

public List<Trolley> findAllTrolley(User user) {
    //ӦΪ�����������Ҫ��ʾuser�����ݺ�goods�����ݣ����ԣ�
	//����trolley����ѯ����Ӧ��goods�����ݣ�����������ӵ�trolley��
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
