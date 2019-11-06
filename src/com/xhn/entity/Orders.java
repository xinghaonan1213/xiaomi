package com.xhn.entity;

import java.util.Date;
import java.util.List;

public class Orders {
	private String orders_number;// 订单编号 1
	private Integer uid;// 用户id 1
	private User user; // 虚拟对象，不存入数据库，只是为了方便使用
	private String ordersName;// 订单名称 1
	private Integer goodsCount;// 商品的件数 1
	private Date create_time;// 订单的生成日期 1
	private Integer state;// 订单的状态 1
	private List<Trolley> trolleys;// 后期展示订单的商品数据，需要将订单中的商品保存起来
	private Double sumPrice;// 订单总价 1

	public String getOrders_number() {
		return orders_number;
	}

	public void setOrders_number(String orders_number) {
		this.orders_number = orders_number;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(Double sumPrice) {
		this.sumPrice = sumPrice;
	}

	public String getOrdersName() {
		return ordersName;
	}

	public void setOrdersName(String ordersName) {
		this.ordersName = ordersName;
	}

	public Integer getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public List<Trolley> getTrolleys() {
		return trolleys;
	}

	public void setTrolleys(List<Trolley> trolleys) {
		this.trolleys = trolleys;
	}

	public Orders(String orders_number, Integer uid, String ordersName, Integer goodsCount, Date create_time,
			Double sumPrice) {
		super();
		this.orders_number = orders_number;
		this.uid = uid;
		this.ordersName = ordersName;
		this.goodsCount = goodsCount;
		this.create_time = create_time;
		this.sumPrice = sumPrice;
	}

	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}
   
}
