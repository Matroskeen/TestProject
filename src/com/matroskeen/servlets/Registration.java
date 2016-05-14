package com.matroskeen.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.matroskeen.beans.UserBean;
import com.matroskeen.dao.UserDAO;
import com.matroskeen.helpful.Email;
import com.matroskeen.helpful.Generator;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("registration.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nickName = request.getParameter("nickname");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		String errors = UserDAO.validateRegisterData(nickName, email);
		
		if (errors.isEmpty()) {
			String token = Generator.getRandomString(64);
			String confirmLink = request.getContextPath() + "/confirm_email?token=" + token;
			UserDAO.register(nickName, email, password, UserBean.ROLE_USER, token);
			new Email().sendRegistrationToken(email, confirmLink);
			
			response.sendRedirect(request.getContextPath() + "/confirm_email");
		} else {
			request.setAttribute("status", "danger");
			request.setAttribute("message", errors);
			request.setAttribute("nickname", nickName);
			request.setAttribute("email", email);
			request.getRequestDispatcher("registration.jsp").forward(request, response);
		}
	}

}
