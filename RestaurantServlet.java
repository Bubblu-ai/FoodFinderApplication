package com.tap.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.tap.DAOimpl.RestaurantDAOimp;
import com.tap.model.Restaurant;


@WebServlet("/restauarnt")
public class RestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RestaurantDAOimp restaurantDAOimp = new RestaurantDAOimp();
		List<Restaurant> restaurants = restaurantDAOimp.getAllRestaurants();
		request.setAttribute("r", restaurants);
		RequestDispatcher rd = request.getRequestDispatcher("restauarnt.jsp");
		rd.forward(request, response);
		
	}

	
}
