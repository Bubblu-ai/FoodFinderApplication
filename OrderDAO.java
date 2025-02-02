package com.tap.DAO;

import java.util.List;

import com.tap.model.Order;

public interface OrderDAO {
	 int addOrder(Order order);
	 Order getOrder(int orderid);
	 void updateOrder(Order order);
	 void deleteOrder(int orderid);
	 List<Order>getAllOrder();
}
