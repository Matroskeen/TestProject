package com.matroskeen.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.matroskeen.beans.TournamentBean;
import com.matroskeen.dao.TournamentDAO;

/**
 * Servlet implementation class Tournament
 */
@WebServlet("/tournaments/*")
public class Tournament extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tournament() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session != null) {
			String status = (String) session.getAttribute("status");
			String message = (String) session.getAttribute("message");
			request.setAttribute("status", status);
			request.setAttribute("message", message);
			session.setAttribute("status", null);
			session.setAttribute("message", null);
		}
		String[] pathParts = request.getPathInfo().split("/");
		try {
			int tournamentId = Integer.parseInt(pathParts[1]);
			TournamentBean tournament = TournamentDAO.find(tournamentId);
			if (tournament == null) {
				response.sendError(404);
			} else {
				request.setAttribute("tournament", tournament);
				request.getRequestDispatcher("/tournament.jsp").forward(request, response);
			}
		} catch (Exception e) {
			response.sendError(404);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
