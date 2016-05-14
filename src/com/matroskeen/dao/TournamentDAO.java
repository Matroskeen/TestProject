package com.matroskeen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.matroskeen.config.ConnectionManager;
import com.matroskeen.beans.Tournament;

public class TournamentDAO {
	
	public static boolean create(String title, byte teamPlayers, byte extraPlayers, long date, String info, String terms,
			byte tournamentStatus) {
		
		String query = "INSERT INTO tournaments (title, team_players, extra_players, date, info, terms, status)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		ConnectionManager conM = new ConnectionManager();
		Connection con = conM.getConnection();
		int rowsAffected = 0;
		
		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, title);
			ps.setByte(2, teamPlayers);
			ps.setByte(3, extraPlayers);
			ps.setLong(4, date);
			ps.setString(5, info);
			ps.setString(6, terms);
			ps.setByte(7, tournamentStatus);
			
			rowsAffected = ps.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return rowsAffected > 0;
	}
	
	public static ArrayList<Tournament> getAll() {
		String query = "SELECT * FROM tournaments";
		
		ConnectionManager conM = new ConnectionManager();
		Connection con = conM.getConnection();
		ResultSet rs = null;
		ArrayList<Tournament> tournaments = new ArrayList<>();
		
		try (PreparedStatement ps = con.prepareStatement(query)) {
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				int teamPlayers = rs.getInt("team_players");
				int extraPlayers = rs.getInt("extra_players");
				long date = rs.getLong("date");
				String info = rs.getString("info");
				String terms = rs.getString("terms");
				byte status = rs.getByte("status");
				Tournament tournament = new Tournament(id, title, teamPlayers, extraPlayers, date, info, terms, status);
				tournaments.add(tournament);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return tournaments;
	}

}
