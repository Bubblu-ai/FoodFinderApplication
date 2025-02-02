package com.tap.model;

public class Restaurant {
	
	private int restaurantid;
	private String name;
	private String address;
	private int phone;
	private double rating;
	private String cusineType;
	private boolean isActive;
	private String eta;
	private int adminUserid;
	private String imagePath;
	
	
	public Restaurant() {}


	public Restaurant(int restaurantid, String name, String address, int phone, double rating, String cusineType,
			boolean isActive, String eta, int adminUserid, String imagePath) {
		super();
		this.restaurantid = restaurantid;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.rating = rating;
		this.cusineType = cusineType;
		this.isActive = isActive;
		this.eta = eta;
		this.adminUserid = adminUserid;
		this.imagePath = imagePath;
	}


	public int getRestaurantid() {
		return restaurantid;
	}


	public void setRestaurantid(int restaurantid) {
		this.restaurantid = restaurantid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public int getPhone() {
		return phone;
	}


	public void setPhone(int phone) {
		this.phone = phone;
	}


	public double getRating() {
		return rating;
	}


	public void setRating(double rating) {
		this.rating = rating;
	}


	public String getCusineType() {
		return cusineType;
	}


	public void setCusineType(String cusineType) {
		this.cusineType = cusineType;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	public String getEta() {
		return eta;
	}


	public void setEta(String eta) {
		this.eta = eta;
	}


	public int getAdminUserid() {
		return adminUserid;
	}


	public void setAdminUserid(int adminUserid) {
		this.adminUserid = adminUserid;
	}


	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
	
}
