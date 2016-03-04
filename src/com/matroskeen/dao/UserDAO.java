package com.matroskeen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.matroskeen.beans.User;
import com.matroskeen.config.ConnectionManager;

public class UserDAO {
	
	public static String validateRegisterData(String nickName, String email) {
		
		String errors = "";
		User user = find(nickName);
		
		if (user != null) {
			errors += "На жаль, цей нікнейм вже зайнято :(<br>";
		}
		user = findByEmail(email);
		
		if (user != null) {
			errors += "Введена електронна пошта використовується іншим користувачем :(";
		}
		return errors;
	}
	
	public static User find(String nickName) {
		String query = "SELECT * FROM users WHERE nickname = ?";
		
		ConnectionManager conM = new ConnectionManager();
		Connection con = conM.getConnection();
		ResultSet rs = null;
		User user = null;
		
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, nickName);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				int id = rs.getInt("id");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String avatar = rs.getString("avatar");
				byte role = rs.getByte("role");
				byte status = rs.getByte("status");
				long registered = rs.getLong("registered");
				user = new User(id, nickName, email, password, avatar, role, status, registered);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return user;
	}
	
	public static User find(int id) {
		String query = "SELECT * FROM users WHERE id = ?";
		
		ConnectionManager conM = new ConnectionManager();
		Connection con = conM.getConnection();
		ResultSet rs = null;
		User user = null;
		
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				String nickName = rs.getString("nickName");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String avatar = rs.getString("avatar");
				byte role = rs.getByte("role");
				byte status = rs.getByte("status");
				long registered = rs.getLong("registered");
				user = new User(id, nickName, email, password, avatar, role, status, registered);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return user;
	}
	
	public static ArrayList<User> find(byte role) {
		String query = "SELECT * FROM users WHERE role = ?";
		
		ConnectionManager conM = new ConnectionManager();
		Connection con = conM.getConnection();
		ResultSet rs = null;
		User user = null;
		ArrayList<User> users = new ArrayList<>();
		
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, role);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				int id = rs.getInt("id");
				String nickName = rs.getString("nickName");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String avatar = rs.getString("avatar");
				byte status = rs.getByte("status");
				long registered = rs.getLong("registered");
				user = new User(id, nickName, email, password, avatar, role, status, registered);
				users.add(user);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return users;
	}
	
	public static User findByEmail(String email) {
		String query = "SELECT * FROM users WHERE email = ?";
		
		ConnectionManager conM = new ConnectionManager();
		Connection con = conM.getConnection();
		ResultSet rs = null;
		User user = null;
		
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, email);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				int id = rs.getInt("id");
				String nickName = rs.getString("nickname");
				String password = rs.getString("password");
				String avatar = rs.getString("avatar");
				byte role = rs.getByte("role");
				byte status = rs.getByte("status");
				long registered = rs.getLong("registered");
				user = new User(id, nickName, email, password, avatar, role, status, registered);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return user;
	}
	
	public static boolean register(String nickName, String email, String password, byte role, String token) {
		
		String query = "INSERT INTO users (nickname, email, password, role, registered) VALUES(?, ?, ?, ?, ?)";
		
		ConnectionManager conM = new ConnectionManager();
		Connection con = conM.getConnection();
		int rowsAffected = 0;
		int userId = 0;
		
		long registered = System.currentTimeMillis();
		
		try (PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, nickName);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setByte(4, role);
			ps.setLong(5, registered);
			
			rowsAffected = ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			
			if (rs.next()) {
				userId = rs.getInt(1);
			}
			
			TokenDAO.addToken(userId, token);
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return rowsAffected > 0;
	}
	
	public static boolean confirmEmail(String token) {
		String query = "UPDATE users SET status = 1"
				+ " WHERE id = (SELECT user_id FROM tokens WHERE token = ?)";
		
		ConnectionManager conM = new ConnectionManager();
		Connection con = conM.getConnection();
		int rowsAffected = 0;
		
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, token);
			
			rowsAffected = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return rowsAffected > 0;
	}
	
	

}
