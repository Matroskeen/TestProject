package com.matroskeen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.jasypt.util.password.StrongPasswordEncryptor;

import com.matroskeen.beans.UserBean;
import com.matroskeen.config.ConnectionManager;

public class UserDAO {
	
	public static String validateRegisterData(String nickName, String email) {
		
		String errors = "";
		UserBean user = find(nickName);
		
		if (user != null) {
			errors += "На жаль, цей нікнейм вже зайнято :(<br>";
		}
		user = findByEmail(email);
		
		if (user != null) {
			errors += "Введена електронна пошта використовується іншим користувачем :(";
		}
		return errors;
	}
	
	public static UserBean find(String nickName) {
		String query = "SELECT * FROM users us INNER JOIN accounts ac ON us.id = ac.user_id WHERE nickname = ?";
		
		ConnectionManager conM = new ConnectionManager();
		Connection con = conM.getConnection();
		ResultSet rs = null;
		UserBean user = null;
		
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
				String steamAccount = rs.getString("steam_account");
				String wotAccount = rs.getString("wot_account");
				user = new UserBean(id, nickName, email, password, avatar, role, status, registered, steamAccount, wotAccount);		
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return user;
	}
	
	public static UserBean find(int id) {
		String query = "SELECT * FROM users us INNER JOIN accounts ac ON us.id = ac.user_id WHERE id = ?";
		
		ConnectionManager conM = new ConnectionManager();
		Connection con = conM.getConnection();
		ResultSet rs = null;
		UserBean user = null;
		
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
				String steamAccount = rs.getString("steam_account");
				String wotAccount = rs.getString("wot_account");
				user = new UserBean(id, nickName, email, password, avatar, role, status, registered, steamAccount, wotAccount);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return user;
	}
	
	public static ArrayList<UserBean> find(byte role) {
		String query = "SELECT * FROM users us INNER JOIN accounts ac ON us.id = ac.user_id WHERE role = ?";
		
		ConnectionManager conM = new ConnectionManager();
		Connection con = conM.getConnection();
		ResultSet rs = null;
		UserBean user = null;
		ArrayList<UserBean> users = new ArrayList<>();
		
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
				String steamAccount = rs.getString("steam_account");
				String wotAccount = rs.getString("wot_account");
				user = new UserBean(id, nickName, email, password, avatar, role, status, registered, steamAccount, wotAccount);
				users.add(user);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return users;
	}
	
	public static UserBean findByEmail(String email) {
		String query = "SELECT * FROM users us INNER JOIN accounts ac ON us.id = ac.user_id WHERE email = ?";
		
		ConnectionManager conM = new ConnectionManager();
		Connection con = conM.getConnection();
		ResultSet rs = null;
		UserBean user = null;
		
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
				String steamAccount = rs.getString("steam_account");
				String wotAccount = rs.getString("wot_account");
				user = new UserBean(id, nickName, email, password, avatar, role, status, registered, steamAccount, wotAccount);
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
		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		password = passwordEncryptor.encryptPassword(password);
		
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
			addAccounts(userId);
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
	
	public static boolean update(UserBean user) {
		String query = "UPDATE users SET password=?, avatar=? WHERE id = ?";

		ConnectionManager conM = new ConnectionManager();
		Connection con = conM.getConnection();
		int rowsAffected = 0;
		
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, user.getPassword());
			ps.setString(2, user.getAvatar());
			ps.setInt(3, user.getId());
			
			rowsAffected = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return rowsAffected > 0;
	}
	
	public static boolean addAccounts(int userId) {
		String query = "INSERT INTO accounts (user_id) VALUES (?)";

		ConnectionManager conM = new ConnectionManager();
		Connection con = conM.getConnection();
		int rowsAffected = 0;
		
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, userId);
	
			rowsAffected = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return rowsAffected > 0;
	}
	
	public static boolean updateAccounts(UserBean user) {
		String query = "UPDATE accounts SET steam_account = ?, wot_account = ? WHERE user_id = ?";

		ConnectionManager conM = new ConnectionManager();
		Connection con = conM.getConnection();
		int rowsAffected = 0;
		
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, user.getSteamAccount());
			ps.setString(2, user.getWotAccount());
			ps.setInt(3, user.getId());
			
			rowsAffected = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return rowsAffected > 0;
	}
	
	public static ArrayList<UserBean> getAll() {
		String query = "SELECT * FROM users us INNER JOIN accounts ac ON us.id = ac.user_id "
				+ "ORDER BY us.role DESC, us.id";
		
		ConnectionManager conM = new ConnectionManager();
		Connection con = conM.getConnection();
		ResultSet rs = null;
		UserBean user = null;
		ArrayList<UserBean> users = new ArrayList<>();
		
		try (PreparedStatement ps = con.prepareStatement(query)) {
			rs = ps.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String nickName = rs.getString("nickname");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String avatar = rs.getString("avatar");
				byte role = rs.getByte("role");
				byte status = rs.getByte("status");
				long registered = rs.getLong("registered");
				String steamAccount = rs.getString("steam_account");
				String wotAccount = rs.getString("wot_account");
				user = new UserBean(id, nickName, email, password, avatar, role, status, registered, steamAccount, wotAccount);
				users.add(user);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return users;
	}

}
