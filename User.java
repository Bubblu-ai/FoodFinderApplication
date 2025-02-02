package com.tap.model;

import java.sql.Timestamp;

public class User {
    private int userid;
    private String name;
    private String username;
    private String password;
    private String email;
    private int phone;
    private String address;
    private String role;
    private Timestamp createdDate;
    private Timestamp lastLoginDate;

    public User() {
        this.createdDate = new Timestamp(System.currentTimeMillis());
        this.lastLoginDate = new Timestamp(System.currentTimeMillis());
    }

    public User(int userid, String name, String username, String password, String email, int phone, String address,
                String role, Timestamp createdDate, Timestamp lastLoginDate) {
        this.userid = userid;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.role = role;
        this.createdDate = createdDate;
        this.lastLoginDate = lastLoginDate;
    }

  
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Timestamp lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
}
