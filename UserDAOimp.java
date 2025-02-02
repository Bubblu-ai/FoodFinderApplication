package com.tap.DAOimpl;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.tap.connection.DBConnection;
import com.tap.DAO.UserDAO;
import com.tap.model.User;

public class UserDAOimp implements UserDAO {

    @Override
    public void addUser(User user) {
        String INSERT_USER_QUERY = "INSERT INTO User (name, username, password, email, phone, address, role) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_QUERY)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setInt(5, user.getPhone());
            preparedStatement.setString(6, user.getAddress());
            preparedStatement.setString(7, user.getRole());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(int userid) {
        String GET_USER_QUERY = "SELECT * FROM User WHERE userid = ?";
        User user = null;

        try (Connection connection = DBConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_QUERY)) {

            preparedStatement.setInt(1, userid);
            ResultSet res = preparedStatement.executeQuery();

            if (res.next()) {
                user = extractUser(res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        String UPDATE_USER_QUERY = "UPDATE User SET name = ?, password = ?, phone = ?, role = ? WHERE userid = ?";

        try (Connection connection = DBConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_QUERY)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getPhone());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setInt(5, user.getUserid());

            int res = preparedStatement.executeUpdate();
            if (res == 0) {
                System.out.println("Update failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int userid) {
        String DELETE_USER_QUERY = "DELETE FROM User WHERE userid = ?";

        try (Connection connection = DBConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_QUERY)) {

            preparedStatement.setInt(1, userid);
            int res = preparedStatement.executeUpdate();

            if (res == 0) {
                System.out.println("Delete failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        String ALL_USER_QUERY = "SELECT * FROM User";
        List<User> usersList = new ArrayList<>();

        try (Connection connection = DBConnection.connect();
             Statement statement = connection.createStatement();
             ResultSet res = statement.executeQuery(ALL_USER_QUERY)) {

            while (res.next()) {
                usersList.add(extractUser(res));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    @Override
    public User validateUser(String email, String password) {
        String VALIDATE_USER_QUERY = "SELECT * FROM User WHERE email = ? AND password = ?";
        User user = null;

        try (Connection connection = DBConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(VALIDATE_USER_QUERY)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet res = preparedStatement.executeQuery();

            if (res.next()) {
                user = extractUser(res);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    private User extractUser(ResultSet res) throws SQLException {
        return new User(
            res.getInt("userid"),
            res.getString("name"),
            res.getString("username"),
            res.getString("password"),
            res.getString("email"),
            res.getInt("phone"),
            res.getString("address"),
            res.getString("role"),
            res.getTimestamp("createdDate"),
            res.getTimestamp("lastLoginDate")
        );
    }
}
