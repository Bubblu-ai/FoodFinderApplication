package com.tap.DAOimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tap.connection.DBConnection;
import com.tap.DAO.OrderDAO;
import com.tap.model.Order;


public class OrderDAOimp implements OrderDAO {

	@Override
	public int addOrder(Order order) {

		String INSERT_ORDER_QUERY = "INSERT INTO `order` (userid, restaurantid, orderDate, totalAmount, status, paymentMode) VALUES (?, ?, ?, ?, ?, ?)";
		
		int Ordereid = -1; // Default value indicating failure
		try(Connection connection = DBConnection.connect();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER_QUERY,Statement.RETURN_GENERATED_KEYS)) {
			
			preparedStatement.setInt(1,order.getUserid());
			preparedStatement.setInt(2, order.getRestaurantid());
			preparedStatement.setTimestamp(3, order.getOrderDate());
			preparedStatement.setDouble(4, order.getTotalAmount());
			preparedStatement.setString(5, order.getStatus());
			preparedStatement.setString(6, order.getPaymentMode());
			
			int res = preparedStatement.executeUpdate();
			
			if(res==0) {
				throw new SQLException("Creating order failed, no rows affected");
			}
			
			try(ResultSet generatedKeys=preparedStatement.getGeneratedKeys()){
				if(generatedKeys.next()) {
					Ordereid=generatedKeys.getInt(1);
					order.setOrdereid(Ordereid);
				}else {
					throw new SQLException("creating order failed , no ID obatined.");
				}
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Ordereid;
	}

	@Override
	public Order getOrder(int orderid) {

		String GET_ORDER_QUERY="SELECT * FROM 'order' WHERE 'orderid'=?";
		Order order=null;
		
		try(Connection connection = DBConnection.connect();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_QUERY)) {
			
			preparedStatement.setInt(1, orderid);
			
			ResultSet res = preparedStatement.executeQuery();
			
			order = extractOrder(res);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return order;
	}

	@Override
	public void updateOrder(Order order) {

		
		String UPDATE_ORDER_QUERY="UPDATE order SET 'userid'=?,'restaurantid'=?,'totalAmount'=?,'status'=?,'paymentMode'=? ";
		
		
		try(Connection connection = DBConnection.connect();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_QUERY)) {
			
			preparedStatement.setInt(1, order.getUserid());
			preparedStatement.setInt(2, order.getRestaurantid());
			preparedStatement.setDouble(4, order.getTotalAmount());
			preparedStatement.setString(5, order.getStatus());
			preparedStatement.setString(6, order.getPaymentMode());
			
			int res = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public void deleteOrder(int orderid) {

		String DELETE_ORDER_QUERY="DELETE FROM 'order' WHERE 'orderid'=?";
		
		
		try(Connection connection = DBConnection.connect();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER_QUERY)){
			
			preparedStatement.setInt(1, orderid);
			
			int res = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Order> getAllOrder() {

		String GETALL_ORDER_QUERY="SELECT * FORM 'order'";
		ArrayList<Order> orderList = new ArrayList<Order>();
		
		try(Connection connection = DBConnection.connect();
			Statement statement = connection.createStatement()) {
			
			ResultSet res = statement.executeQuery(GETALL_ORDER_QUERY);
			
			while(res.next()) {
				Order order = extractOrder(res);
				orderList.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}


	
	 Order extractOrder(ResultSet res) throws SQLException{

			int orderid=res.getInt("orderid");
			int userid=res.getInt("userid");
			int restaurantid=res.getInt("restaurantid");
			Timestamp orderDate = res.getTimestamp("orderDate");
			long totalAmount=res.getLong("totalAmount");
			String status=res.getString("status");
			String paymentMode=res.getString("paymentMode");
			
				Order order = new Order(orderid, userid, restaurantid, orderDate, totalAmount, status, paymentMode);
			return order;
			
	
	 }

}
