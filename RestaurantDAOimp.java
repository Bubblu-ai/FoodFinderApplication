package com.tap.DAOimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tap.connection.DBConnection;
import com.tap.DAO.RestaurantDAO;
import com.tap.model.Restaurant;


public class RestaurantDAOimp implements RestaurantDAO{

	@Override
	public void addRestaurant(Restaurant restaurant) {
			String INSERT_RESTAURANT_QUERY="INSERT INTO `restaurant` (`name`, `address`, `phone`, 'rating', 'cusineType', 'isAvailable', 'eta', 'adminUserid', 'imagepath') VALUES(? ,? ,? , ? , ? ,? ,? ,? ,?)";
	
				
				try(Connection connection = DBConnection.connect();
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RESTAURANT_QUERY)) {
					
					
					preparedStatement.setString(1,restaurant.getName());
					preparedStatement.setString(2, restaurant.getAddress());
					preparedStatement.setInt(3, restaurant.getPhone());
					preparedStatement.setDouble(4, restaurant.getRating());
					preparedStatement.setString(5, restaurant.getCusineType());
					preparedStatement.setBoolean(6, restaurant.isActive());
					preparedStatement.setString(7, restaurant.getEta());
					preparedStatement.setInt(8, restaurant.getAdminUserid());
					preparedStatement.setString(9, restaurant.getImagePath());
					
					
					preparedStatement.executeUpdate();
						
				} catch (SQLException e) {
					e.printStackTrace();
				}
	}

	@Override
	public Restaurant getRestaurant(int restaurantid) {
        String GET_RESTAURANT_QUERY = "SELECT * FROM `restaurant` WHERE `restaurantid`=?";
        Restaurant restaurant = null;

        try (Connection connection = DBConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_RESTAURANT_QUERY)) {

            preparedStatement.setInt(1, restaurantid);
            ResultSet res = preparedStatement.executeQuery();

            if (res.next()) {  
                restaurant = extractrestaurant(res);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurant;
    }

	@Override
	public void updateRestaurant(Restaurant restaurant) {
		String UPDATE_RESTAURANT_QUERY = "UPDATE `restaurant` SET `name`=?, `address`=?, `phone`=?, `rating`=?, `cusineType`=?, `isAvailable`=?, `eta`=?, `adminUserid`=?, `imagepath`=? WHERE `restaurantid`=?";
	
		
		Connection connection = DBConnection.connect();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_RESTAURANT_QUERY);
			
			preparedStatement.setString(1, restaurant.getName());  
			preparedStatement.setString(2, restaurant.getAddress());
			preparedStatement.setInt(3, restaurant.getPhone());
			preparedStatement.setDouble(4, restaurant.getRating());
			preparedStatement.setString(5, restaurant.getCusineType());
			preparedStatement.setBoolean(6, restaurant.isActive());
			preparedStatement.setString(7, restaurant.getEta());
			preparedStatement.setInt(8, restaurant.getAdminUserid());
			preparedStatement.setString(9, restaurant.getImagePath());
			preparedStatement.setInt(10, restaurant.getRestaurantid());

			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteRestaurant(int restaurantid) {

		String DELETE_RESTAURANT_QUERY=	"DELETE FROM 'restaurant' WHERE 'restaurantId'=?";
		
		
		try(Connection connection = DBConnection.connect();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_RESTAURANT_QUERY)) {
		
			
			preparedStatement.setInt(1, restaurantid);
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
        String GETALL_RESTAURANT_QUERY = "SELECT * FROM `restaurant`";
        List<Restaurant> restaurantList = new ArrayList<>();

        try (Connection connection = DBConnection.connect();
             Statement statement = connection.createStatement();
             ResultSet res = statement.executeQuery(GETALL_RESTAURANT_QUERY)) {

            while (res.next()) {
                restaurantList.add(extractrestaurant(res));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return restaurantList;
    }
		
	
	Restaurant extractrestaurant(ResultSet res) throws SQLException {
	    return new Restaurant(
	        res.getInt("restaurantid"),
	        res.getString("name"),
	        res.getString("address"),
	        res.getInt("phone"),
	        res.getDouble("rating"),
	        res.getString("cusineType"),
	        res.getBoolean("isAvailable"),
	        res.getString("eta"),
	        res.getInt("adminUserid"),
	        res.getString("imagepath")
	    );
	}	
}
