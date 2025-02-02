package com.tap.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.tap.DAOimpl.MenuDAOimp;
import com.tap.DAOimpl.RestaurantDAOimp;
import com.tap.model.Menu;
import com.tap.model.Restaurant;


@WebServlet("/menu")
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
					int id =Integer.parseInt(request.getParameter("restaurantid"));
					
					
					RestaurantDAOimp restaurantDAO = new RestaurantDAOimp();
					Restaurant restaurant = restaurantDAO.getRestaurant(id);
					
					MenuDAOimp menuDAOimp = new MenuDAOimp();
					List<Menu> allMenuByRestaurant = menuDAOimp.getAllMenuByRestaurant(id);
					
					request.setAttribute("restaurantid", id);
					request.setAttribute("m", allMenuByRestaurant);
					request.setAttribute("restaurant", restaurant);
					
				    request.getSession().setAttribute("restaurantid", id);  
					
					RequestDispatcher rd = request.getRequestDispatcher("menu.jsp");
					rd.forward(request, response);
	}
}
