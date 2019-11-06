package com.xhn.service;

import java.util.List;

import com.xhn.dao.GoodsDao;
import com.xhn.entity.Category;
import com.xhn.entity.Goods;
import com.xhn.untils.PageTool;

public class GoodsService {
    private GoodsDao goodsDao=new GoodsDao();
    private CategoryService cService=new CategoryService();
	public boolean insertGoods(Goods goods) {
		int row=goodsDao.insertGoods(goods);
		if (row>0) {
			return true;
		}
		return false;
	}

	public int selectGoodsCount() {
		return goodsDao.selectGoodsCount();
	}

	public List<Goods> findAllGoods(PageTool pageTool) {
		 List<Goods> goodes = goodsDao.findAllGoods(pageTool);
		 //遍历获取获取到goods中的cid值，并通过category的findcateById方法获取cid对应的cname值
		 for (Goods goods : goodes) {
			Category category = cService.findCateById(String.valueOf(goods.getCid()));
			goods.setCategory(category);
		}
		return goodes;
	}

	public Goods findGoodsById(String gid) {
		
		return goodsDao.findGoodsById(gid);
	}

	public boolean updateGoods(Goods goods) {
        int row=goodsDao.updateGoods(goods);
        if (row>0) {
			return true;
		}
		return false;
	}

	public List<Goods> findGoodsByState(int state, int count) {
	
		return goodsDao.findGoodsByState(state,count);
	}

	public List<Goods> finGoodsByCate(int cid, int count) {
		return goodsDao.finGoodsByCate(cid,count);
	}

	public boolean deleteById(String ids) {
		int row=goodsDao.deleteById(ids);
		if (row>0) {
			return true;
		}
		return false;
	}

	public List<Goods> finGoodsBySearch(String info, int count) {
		
		return goodsDao.finGoodsBySearch(info,count);
	}
}
