package com.tap.servlets;

import java.io.IOException;

import com.tap.DAO.UserDAO;
import com.tap.DAOimpl.UserDAOimp;
import com.tap.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAOimp();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("Email");
        String password = req.getParameter("Password");

        User user = userDAO.validateUser(email, password);

        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("loggedInUser", user);
            resp.sendRedirect("restauarnt"); // Redirect to restaurant page
        } else {
            req.setAttribute("errorMessage", "Invalid email or password!");
            RequestDispatcher rd = req.getRequestDispatcher("login.html");
            rd.forward(req, resp);
        }
    }
}
