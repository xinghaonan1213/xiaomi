package com.xhn.service;

import com.xhn.dao.OrdersDao;
import com.xhn.entity.Orders;

public class OrdersService {
    private OrdersDao ordersDao=new OrdersDao();
	public boolean createOrders(Orders orders) {
	    int row=ordersDao.createOrders(orders);
	    if (row>0) {
			return true;
		}
		return false;
	}
	public boolean updateOrders(String orders_number) {
		 int row=ordersDao.updateOrders(orders_number);
		    if (row>0) {
				return true;
			}
			return false;
	}

}
