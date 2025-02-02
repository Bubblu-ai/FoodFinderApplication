package com.tap.servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;

import com.tap.DAO.OrderDAO;
import com.tap.DAO.OrderitemDAO;
import com.tap.DAOimpl.OrderDAOimp;
import com.tap.DAOimpl.OrderitemDAOimp;
import com.tap.model.Cart;
import com.tap.model.CartItem;
import com.tap.model.Order;
import com.tap.model.Orderitem;
import com.tap.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private OrderDAO orderDAO;
    private OrderitemDAO orderitemDAO;

    @Override
    public void init() {
        try {
            orderDAO = new OrderDAOimp();
            orderitemDAO = new OrderitemDAOimp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("loggedInUser");

        if (cart != null && user != null && !cart.getItems().isEmpty()) {
            String paymentMethod = request.getParameter("paymentMethod");
            double totalAmount = 0;
            for (CartItem item : cart.getItems().values()) {
                totalAmount += item.getPrice() * item.getQuantity();
            }

            Order order = new Order();
            order.setUserid(user.getUserid());

            // Fix: Get restaurantid from session
            Integer restaurantid = (Integer) session.getAttribute("restaurantid");
            if (restaurantid == null) {
                System.out.println("Error: restaurantid is null in Checkout Servlet");
                response.sendRedirect("menu.jsp");
                return;
            }
            order.setRestaurantid(restaurantid);

            order.setOrderDate(new Timestamp(System.currentTimeMillis()));
            order.setPaymentMode(paymentMethod);
            order.setStatus("Pending");
            order.setTotalAmount(totalAmount);

            orderDAO.addOrder(order);

            for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
                CartItem cartItem = entry.getValue();
                Orderitem orderitem = new Orderitem();
                orderitem.setOrderid(order.getOrdereid());
                orderitem.setMenuid(cartItem.getItemid());
                orderitem.setQuantity(cartItem.getQuantity());
                orderitem.setTotalPrice(cartItem.getPrice() * cartItem.getQuantity());

                orderitemDAO.addOrderitem(orderitem);
            }

            session.removeAttribute("cart");
            session.setAttribute("order", order);
            response.sendRedirect("orderconfirmation.jsp");
        } else {
            System.out.println("Order placement failed, staying on the same page.");
            response.sendRedirect("checkout.jsp");
        }
    }


}
