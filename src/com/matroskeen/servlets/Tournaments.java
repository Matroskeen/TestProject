package com.matroskeen.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.matroskeen.beans.Tournament;
import com.matroskeen.beans.User;
import com.matroskeen.dao.TournamentDAO;

/**
 * Servlet implementation class Tournaments
 */
@WebServlet("/tournaments")
public class Tournaments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Tournaments() {
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
		
		ArrayList<Tournament> tournaments = TournamentDAO.getAll();
		ArrayList<Tournament> activeTournaments = new ArrayList<>();
		ArrayList<Tournament> plannedTournaments = new ArrayList<>();
		ArrayList<Tournament> archiveTournaments = new ArrayList<>();
		
		for(Tournament tournament : tournaments) {
			switch(tournament.getStatus()) {
				case Tournament.STATUS_PAST:
					archiveTournaments.add(tournament);
					break;
				case Tournament.STATUS_PLANNED:
					plannedTournaments.add(tournament);
					break;
				case Tournament.STATUS_ACTIVE:
					activeTournaments.add(tournament);
					break;
			}
		}
		Map<Byte, ArrayList<Tournament>> sortedTournaments  = new HashMap<>();
		sortedTournaments.put(Tournament.STATUS_PAST, archiveTournaments);
		sortedTournaments.put(Tournament.STATUS_PLANNED, plannedTournaments);
		sortedTournaments.put(Tournament.STATUS_ACTIVE, activeTournaments);
		
		request.setAttribute("tournaments", sortedTournaments);
		request.getRequestDispatcher("tournaments.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User user = (session != null) ? (User) session.getAttribute("user") : null;
		
		if (user == null || user.getRole() != User.ROLE_ADMIN) {
			response.sendError(403);
		} else {
			String title = request.getParameter("title");
			String sTeamPlayers = request.getParameter("team_players");
			String sExtraPlayers = request.getParameter("extra_players");
			String sDate = request.getParameter("date"); // It's 00:00 time of the date.
			String info = request.getParameter("info");
			String order = request.getParameter("order");
			String status = request.getParameter("status");
			
			byte teamPlayers = Byte.parseByte(sTeamPlayers);
			byte extraPlayers = sExtraPlayers.isEmpty() ? 0 : Byte.parseByte(sExtraPlayers);
			byte tournamentStatus = Byte.parseByte(status);
					
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			long date = 0;
	        try {
	        	Date d = dateFormat.parse(sDate);
		        date = d.getTime();
	        } catch (ParseException e) {
		        e.printStackTrace();
	        }
			
			if (TournamentDAO.create(title, teamPlayers, extraPlayers, date, info, order, tournamentStatus)) {
				session.setAttribute("status", "success");
				session.setAttribute("message", "������ ������ ������!");
				response.sendRedirect(request.getContextPath() + "/tournaments");
			} else {
				request.setAttribute("status", "warning");
				request.setAttribute("message", "������� �������� ��������. ��������� �� ���.");
				request.setAttribute("order", order);
				request.setAttribute("info", info);
				request.setAttribute("title", title);
				request.getRequestDispatcher("add_tournament.jsp").forward(request, response);
			}
		}
	}

}
