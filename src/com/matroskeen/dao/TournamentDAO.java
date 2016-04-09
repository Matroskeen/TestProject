package com.matroskeen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.matroskeen.config.ConnectionManager;

public class TournamentDAO {
	
	public static boolean create(String title, int teamPlayers, int extraPlayers, long date, String info, String order) {
		
		String query = "INSERT INTO tournaments (title, team_players, extra_players, date, info, terms)"
				+ " VALUES (?, ?, ?, ?, ?, ?)";
		
		ConnectionManager conM = new ConnectionManager();
		Connection con = conM.getConnection();
		int rowsAffected = 0;
		
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, title);
			ps.setInt(2, teamPlayers);
			ps.setInt(3, extraPlayers);
			ps.setLong(4, date);
			ps.setString(5, info);
			ps.setString(6, order);
			
			rowsAffected = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return rowsAffected > 0;
	}

}
