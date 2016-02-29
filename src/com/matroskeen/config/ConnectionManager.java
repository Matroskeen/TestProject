package com.matroskeen.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {

	public Connection getConnection() {
		Connection con = null;
		
		try {
			String url = "jdbc:mysql://localhost:3306/test_project";
			String admin = "root";
			String password = "root";

			Class.forName("com.mysql.jdbc.Driver"); 
			con = DriverManager.getConnection(url, admin, password);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return con;
	}
}

