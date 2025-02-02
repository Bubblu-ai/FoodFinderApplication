package com.tap.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Order {
	private int ordereid;
	private int userid;
	private int restaurantid;
	private Timestamp orderDate;
	private double totalAmount;
	private String status;
	private String paymentMode;
	private List<Orderitem>orderItem;
	
	public Order(){
		this.orderDate=new Timestamp(System.currentTimeMillis());
		this.orderItem=new ArrayList<>();
	}

	public Order(int ordereid, int userid, int restaurantid, Timestamp orderDate, double totalAmount, String status,
			String paymentMode) {
		super();
		this.ordereid = ordereid;
		this.userid = userid;
		this.restaurantid = restaurantid;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.status = status;
		this.paymentMode = paymentMode;
	}

	public int getOrdereid() {
		return ordereid;
	}

	public void setOrdereid(int ordereid) {
		this.ordereid = ordereid;
	}

	public int getUserid() {
		return userid;
	}
	
	
	public List<Orderitem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<Orderitem> orderItem) {
		this.orderItem = orderItem;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getRestaurantid() {
		return restaurantid;
	}

	public void setRestaurantid(int restaurantid) {
		this.restaurantid = restaurantid;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	
	
}
