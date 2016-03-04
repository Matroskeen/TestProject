package com.matroskeen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.matroskeen.config.ConnectionManager;

public class TokenDAO {
	
	public static boolean addToken(int userId, String token) {
		String query = "INSERT INTO tokens VALUES(?, ?)";
		
		ConnectionManager conM = new ConnectionManager();
		Connection con = conM.getConnection();
		int rowsAffected = 0;
		
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, userId);
			ps.setString(2, token);
			
			rowsAffected = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return rowsAffected > 0;
	}
	
	public static boolean deleteToken(String token) {
		String query = "DELETE FROM tokens WHERE token = ?";
		
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
