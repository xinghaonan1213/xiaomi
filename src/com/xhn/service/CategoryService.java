package com.xhn.service;

import java.util.List;

import com.sun.org.apache.regexp.internal.recompile;
import com.xhn.dao.CategoryDao;
import com.xhn.entity.Category;
import com.xhn.untils.PageTool;

public class CategoryService {
    private CategoryDao cDao=new CategoryDao();

	public int selectCateCount() {
		return cDao.selectCateCount();
	}

	public List<Category> findAllCategory(PageTool pageTool) {
		
		return cDao.findAllCategory(pageTool);
	}

	public boolean insertCate(Category category) {
		int row=cDao.insertCate(category);
		if (row>0) {
			return true;
		}
		return false;
	}

	public Category findCateById(String cid) {
		
		return cDao.findCateById(cid);
	}

	public boolean updateCate(Category category) {
		int row=cDao.updateCate(category);
		if (row>0) {
			return true;
		}
		return false;
	}

	public boolean deleteById(String ids) {
		int row=cDao.deleteById(ids);
		if (row>0) {
			return true;
		}
		return false;
	}

	public List<Category> findAllCate() {
		return cDao.findAllCate();
	}

	public List<Category> findAllCate(int count) {
		// TODO Auto-generated method stub
		return cDao.findAllCate(count);
	}
}
