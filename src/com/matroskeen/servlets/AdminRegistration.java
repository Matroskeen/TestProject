package com.matroskeen.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.matroskeen.beans.User;
import com.matroskeen.dao.UserDAO;
import com.matroskeen.helpful.Generator;

/**
 * Servlet implementation class AdminRegistration
 */
@WebServlet("/admin")
public class AdminRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminRegistration() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (UserDAO.find(User.ROLE_ADMIN).isEmpty()) {
			request.getRequestDispatcher("admin-registration.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath());
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nickName = request.getParameter("nickname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		if (UserDAO.find(User.ROLE_ADMIN).isEmpty()) {
			String token = Generator.getRandomString(64);
			
			UserDAO.register(nickName, email, password, User.ROLE_ADMIN, token);
		}
		response.sendRedirect(request.getContextPath());
	}

}
