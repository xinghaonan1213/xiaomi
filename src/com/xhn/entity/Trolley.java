package com.xhn.entity;

public class Trolley {
   private Integer tid;//���ﳵ����
   private Integer uid;//�û�����
   private User user;
   private Integer gid;//��Ʒ����
   private Goods goods;
   public Integer getTid() {
	return tid;
}
public void setTid(Integer tid) {
	this.tid = tid;
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
public Integer getGid() {
	return gid;
}
public void setGid(Integer gid) {
	this.gid = gid;
}
public Goods getGoods() {
	return goods;
}
public void setGoods(Goods goods) {
	this.goods = goods;
}
public Integer getNumber() {
	return number;
}
public void setNumber(Integer number) {
	this.number = number;
}
public String getOrders_number() {
	return orders_number;
}
public void setOrders_number(String orders_number) {
	this.orders_number = orders_number;
}
private Integer number;//��Ʒ������
private String orders_number;//Ĭ��Ϊnull����ʾ��ǰδ�����
public Trolley(Integer uid, Integer gid) {
	super();
	this.uid = uid;
	this.gid = gid;
}
public Trolley() {
	super();
	// TODO Auto-generated constructor stub
}
@Override
public String toString() {
	return "Trolley [tid=" + tid + ", uid=" + uid + ", user=" + user + ", gid=" + gid + ", goods=" + goods + ", number="
			+ number + ", orders_number=" + orders_number + "]";
}
   
}
