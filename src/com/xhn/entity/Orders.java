package com.xhn.entity;

import java.util.Date;
import java.util.List;

public class Orders {
	private String orders_number;// ������� 1
	private Integer uid;// �û�id 1
	private User user; // ������󣬲��������ݿ⣬ֻ��Ϊ�˷���ʹ��
	private String ordersName;// �������� 1
	private Integer goodsCount;// ��Ʒ�ļ��� 1
	private Date create_time;// �������������� 1
	private Integer state;// ������״̬ 1
	private List<Trolley> trolleys;// ����չʾ��������Ʒ���ݣ���Ҫ�������е���Ʒ��������
	private Double sumPrice;// �����ܼ� 1

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
