package com.tap.DAOimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.DAO.MenuDAO;
import com.tap.connection.DBConnection;
import com.tap.model.Menu;



public class MenuDAOimp implements MenuDAO {

	@Override
	public void addMenu(Menu menu) {
			String INSERT_MENU_QUREY="INSERT INTO 'Menu (itemName, description, price, rating, isAvailable, imagePath) VALUES (?, ?, ?, ?, ?, ?)";
				try(Connection connection = DBConnection.connect();
					PreparedStatement preparedstatement = connection.prepareStatement(INSERT_MENU_QUREY)) {
					
					preparedstatement.setString(1, menu.getItemName());
					preparedstatement.setString(2, menu.getDescription());
					preparedstatement.setDouble(3, menu.getPrice());
					preparedstatement.setFloat(4, menu.getRating());
					preparedstatement.setBoolean(5, menu.getIsAvailable());
					preparedstatement.setString(6, menu.getImagePath());
			
					int res = preparedstatement.executeUpdate();
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
	}

	@Override
	public Menu getMenu(int menuid) {
		String GET_MENU_QUREY="SELECT * FROM Menu WHERE MenuID=?";
		
		try(Connection connection = DBConnection.connect();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_MENU_QUREY)) {
			
			preparedStatement.setInt(1, menuid);
			
			 ResultSet res = preparedStatement.executeQuery();
			 
			 if(res.next()) {
				 return extractMenu(res);
			 }
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
		
	}

	@Override
	public void updateMenu(Menu menu) {
		String 	UPDATE_MENU_QUREY="UPDATE 'Menu' SET 'itemName'=? ,'description'=?,'price'=?,'rating'=?, 'isAvailable'=?,'imagePath'=?";
		
		try(Connection connection = DBConnection.connect();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MENU_QUREY);) {
			
			preparedStatement.setString(1, menu.getItemName());
			preparedStatement.setString(2, menu.getDescription());
			preparedStatement.setDouble(3, menu.getPrice());
			preparedStatement.setFloat(3, menu.getRating());
			preparedStatement.setBoolean(4, menu.getIsAvailable());
			preparedStatement.setString(4, menu.getImagePath());
			
			
			int res = preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteMenu(int menuid) {
			String DELETE_MENU_QUREY="DELETE from 'Menu' WHERE 'menuid'=?";
			
			try(Connection connection = DBConnection.connect();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MENU_QUREY)) {
				
				preparedStatement.setInt(1, menuid);
				
				int res = preparedStatement.executeUpdate();
		
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	@Override
	public List<Menu> getAllMenuByRestaurant(int restaurantid) {
		String GETALL_MENU_QUREY="SELECT * FROM `Menu` WHERE `restaurantid`=? ";
		
		ArrayList<Menu> menuList = new ArrayList<Menu>();
		
		
		try (Connection connection = DBConnection.connect();
				PreparedStatement preparedstatement=connection.prepareStatement(GETALL_MENU_QUREY)){
			
			preparedstatement.setInt(1, restaurantid);
			
			ResultSet res = preparedstatement.executeQuery();
							
			
			while(res.next()) {
				Menu menu = extractMenu(res);
				menuList.add(menu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menuList;
	}

	

	
	 Menu extractMenu(ResultSet res) throws SQLException{

			int menuid=res.getInt("menuid");
			int restaurantid =res.getInt("restaurantid");
			String itemName = res.getString("itemName");
			String description = res.getString("description");
			int price = res.getInt("price");
			float rating = res.getFloat("rating");
			boolean isAvailable = res.getBoolean("isAvailable");
			String imagePath = res.getString("imagePath");
			
			Menu menu = new Menu(menuid, restaurantid, itemName, description, price, isAvailable, imagePath);
			return menu;
	
	 }
	
	 public static void main(String[] args) {
		MenuDAOimp menuDAOimp = new MenuDAOimp();
		
		List<Menu> allMenuByRestaurant = menuDAOimp.getAllMenuByRestaurant(1);
		
		for ( Menu menu : allMenuByRestaurant) {
			System.out.println(menu);
		}
	}

}
