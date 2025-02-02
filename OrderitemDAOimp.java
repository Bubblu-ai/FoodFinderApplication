package com.tap.DAOimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.connection.DBConnection;
import com.tap.DAO.OrderitemDAO;
import com.tap.model.Orderitem;


public class OrderitemDAOimp implements OrderitemDAO {

	@Override
	public void  addOrderitem(Orderitem orderItem) {
	    String sql = "INSERT INTO orderitem ( orderid, menuid, quantity, price) VALUES (?, ?, ?,?)";
	    
	    try (Connection con = DBConnection.connect();
	         PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {  // Request generated keys

	        ps.setInt(1, orderItem.getOrderid());
	        ps.setInt(2, orderItem.getMenuid());
	        ps.setInt(3, orderItem.getQuantity());
	        ps.setDouble(4, orderItem.getTotalPrice());

	        	int res = ps.executeUpdate();
			
			if(res==0) {
				throw new SQLException("Creating order failed, no rows affected");
			}
			
	        try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	orderItem.setOrderitemid(generatedKeys.getInt(1));  // Return generated key (e.g., order item ID)
	            } else {
	                throw new SQLException("Creating order item failed, no ID obtained.");
	            }
	        }
	    }
	    catch (Exception e) {
	    		e.printStackTrace();
	    }
	}


	@Override
	public Orderitem getOrderitem(int orderitemid) {

		String GET_ORDERITEM_QUERY="SELECT * FROM 'orderitem' WHERE 'orderitemid'=?";
		Orderitem orderitem=null;
		
		try(Connection connection = DBConnection.connect();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDERITEM_QUERY)) {
			
			
			preparedStatement.setInt(1, orderitemid);
			
			ResultSet res = preparedStatement.executeQuery();
			
			orderitem = extractOrderitem(res);
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderitem;
	}

	@Override
	public void updateOrderitem(Orderitem orderitem) {

		String UPDATE_ORDERITEM_QUERY="UPDATE 'orderitem' SET 'orderid'=?, 'menuid'=?, 'quantity'=?, 'totalPrice'=? ";
		
		try(Connection connection = DBConnection.connect();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDERITEM_QUERY)) {
			
			preparedStatement.setInt(1, orderitem.getOrderid());
			preparedStatement.setInt(2, orderitem.getMenuid());
			preparedStatement.setInt(3, orderitem.getQuantity());
			preparedStatement.setDouble(4, orderitem.getTotalPrice());
			
			int res = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteOrderitem(int orderitemid) {

		String  DELETE_ORDERITEM_QUERY="DELETE FROM 'orderitemid' WHERE 'orderitemid'=?";
		
		try(Connection connection = DBConnection.connect();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDERITEM_QUERY)){
			
			
			preparedStatement.setInt(1, orderitemid);
			
			int res = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Orderitem> getAllOrderitemByOrder(int orderid) {

		String GETALL_ORDERITEM_QUERY="SELECT * FROM `orderitemid`";
		
		ArrayList<Orderitem> orderitemList = new ArrayList<Orderitem>();
		
		try(Connection connection = DBConnection.connect();
			Statement statement = connection.createStatement()){
			
			ResultSet res = statement.executeQuery(GETALL_ORDERITEM_QUERY);
			
					while(res.next()) {
						Orderitem orderitem = extractOrderitem(res);
						orderitemList.add(orderitem);
					}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderitemList;
	}

	

	Orderitem extractOrderitem(ResultSet res) throws SQLException{

		int Orderitem=res.getInt("Orderitemid");
		int orderid=res.getInt("orderid");
		int menuid=res.getInt("menuid");
		int quantity=res.getInt("quantity");
		long totalPrice=res.getLong("totalPrice");
		
		Orderitem orderitem = new Orderitem(Orderitem, orderid, menuid, quantity, totalPrice);
		
		return orderitem;
	}
}
