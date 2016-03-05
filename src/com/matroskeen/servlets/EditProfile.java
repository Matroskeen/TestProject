package com.matroskeen.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.matroskeen.beans.User;
import com.matroskeen.dao.UserDAO;
import com.matroskeen.helpful.FileUploadManager;

/**
 * Servlet implementation class EditProfile
 */
@WebServlet("/edit_profile")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*5,      // 5MB
maxRequestSize=1024*1024*10)   // 10MB
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User user = (session != null) ? (User) session.getAttribute("user") : null;
		
		if (user == null) {
			response.sendError(403);
		} else {
			String status = (String) session.getAttribute("status");
			String message = (String) session.getAttribute("message");
			
			request.setAttribute("status", status);
			request.setAttribute("message", message);
			session.setAttribute("status", null);
			session.setAttribute("message", null);
			
			request.getRequestDispatcher("edit-profile.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User user = (session != null) ? (User) session.getAttribute("user") : null;
		
		if (user != null) {
			String steamAccount = request.getParameter("steam_account");
			String wotAccount = request.getParameter("wot_account");
			String password = request.getParameter("password");
			
			// Save uploaded file, and retrieve his path.
			String avatarName = FileUploadManager.uploadFile(user.getId(), "avatar", request, session);
			
			if (password != null && !password.isEmpty()) {
				user.setPassword(password);
			}
			user.setSteamAccount(steamAccount);
			user.setWotAccount(wotAccount);
			user.setAvatar(avatarName);
			
			if (UserDAO.update(user) && UserDAO.updateAccounts(user)) {
				session.setAttribute("status", "success");
				session.setAttribute("message", "Дані успішно збережені!");
			} else {
				session.setAttribute("status", "warning");
				session.setAttribute("message", "Щось пішло не так.");
			}
			response.sendRedirect(request.getContextPath() + "/edit_profile");
		} else {
			response.sendError(403);
		}	
	}
}
