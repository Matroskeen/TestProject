package com.matroskeen.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.matroskeen.dao.TokenDAO;
import com.matroskeen.dao.UserDAO;

/**
 * Servlet implementation class ConfirmEmail
 */
@WebServlet("/confirm_email")
public class ConfirmEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmEmail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String token = request.getParameter("token");
		
		if (token != null) {
			if (UserDAO.confirmEmail(token)) {
				TokenDAO.deleteToken(token);
				request.setAttribute("confirmed", true);
			}
		}
		
		request.getRequestDispatcher("confirm_email.jsp").forward(request, response);
	}

}
