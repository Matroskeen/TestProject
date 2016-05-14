package com.matroskeen.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.matroskeen.beans.UserBean;
import com.matroskeen.dao.UserDAO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		UserBean user = UserDAO.find(login);
		
		if (user != null && user.isPasswordValid(password)) {
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);
			response.sendRedirect(request.getContextPath() + "/");
		} else {
			request.setAttribute("status", "danger");
			request.setAttribute("message", "Невірний логін, або пароль!");
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}
		
		
		
	}

}
