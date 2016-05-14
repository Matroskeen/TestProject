package com.matroskeen.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.matroskeen.beans.User;

/**
 * Servlet implementation class AddTournament
 */
@WebServlet("/add_tournament")
public class AddTournament extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTournament() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User user = (session != null) ? (User) session.getAttribute("user") : null;
		
		if (user == null || user.getRole() != User.ROLE_ADMIN) {
			response.sendError(403);
		} else {
			String status = (String) session.getAttribute("status");
			String message = (String) session.getAttribute("message");
			
			request.setAttribute("status", status);
			request.setAttribute("message", message);
			session.setAttribute("status", null);
			session.setAttribute("message", null);
			
			request.getRequestDispatcher("add_tournament.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
