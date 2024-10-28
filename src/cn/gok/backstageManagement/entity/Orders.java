package cn.gok.backstageManagement.entity;

import java.util.Random;

public class Orders {
	private int id;//订单号
	private String number;//订单编码
	private String user;//下单用户
	private String time;//下单时间
	private String name;//收件人
	private String sex;//性别
	private String address;//收货地址
	private String phone;//电话号码
	private String address_label;//地址标签
	private int goods_id;//商品编号
	private int goods_num;//订单数量
	private int goods_status;//订单状态
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress_label() {
		return address_label;
	}
	public void setAddress_label(String address_label) {
		this.address_label = address_label;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public int getGoods_num() {
		return goods_num;
	}
	public void setGoods_num(int goods_num) {
		this.goods_num = goods_num;
	}
	public int getGoods_status() {
		return goods_status;
	}
	public void setGoods_status(int goods_status) {
		this.goods_status = goods_status;
	}
	
	public String getRandomString(int length) { // length 字符串长度
		StringBuffer buffer = new StringBuffer("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int range = buffer.length();
		for (int i = 0; i < length; i ++) {
		sb.append(buffer.charAt(r.nextInt(range)));
		}
		return sb.toString();
	}
}
