package com.tap.DAO;

import java.util.List;

import com.tap.model.Orderitem;

public interface OrderitemDAO {
	void addOrderitem(Orderitem orderitem);
	Orderitem getOrderitem(int orderitemid);
	 void updateOrderitem(Orderitem orderitem);
	 void deleteOrderitem(int orderitemid);
	 List<Orderitem>getAllOrderitemByOrder(int orderid);
}
