package com.tap.servlets;



import java.io.IOException;

import com.tap.DAO.MenuDAO;
import com.tap.DAOimpl.MenuDAOimp;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class Cartservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

//	get session
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		
//		check if the cart exist or not if not add 
		
		if(cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
	
//	create Mneu DAO get details of menu
		
		String action = request.getParameter("action");
		int itemid = Integer.parseInt(request.getParameter("itemid"));
		
		MenuDAO menuDAO=null;
		try {
			 menuDAO = new MenuDAOimp();
		} catch(Exception e) {
			e.printStackTrace();
		}
	
		
		try {
//			check the item is added or not based on action
			if(action.equals("add")) {
				Menu menuItem = menuDAO.getMenu(itemid);
				if(menuItem != null) {
					int quantity=1;
					CartItem cartItem = new CartItem(
							menuItem.getMenuid(),
							menuItem.getRestaurantid(),
							menuItem.getItemName(),
							quantity,
							menuItem.getPrice());
					cart.addItem(cartItem);
				}
				
			}
			else if(action.equals("update")) {
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				cart.updateItem(itemid, quantity);
			} else if(action.equals("remove")) {
				cart.removeItem(itemid);
			}
		}catch (NumberFormatException e) {
			e.printStackTrace();
		}
	
		session.setAttribute("cart", cart);
		response.sendRedirect("cart.jsp");
	
	}

}
