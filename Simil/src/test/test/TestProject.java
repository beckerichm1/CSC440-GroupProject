package test;

import java.sql.*;

public class TestProject {
	// public boolean insert

	// Need inserts for rating, user

	// user(username, about, first, last, email, birth, join, locat, type,
	// settings, outRate, inRate, pw)

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/simul_db";
		String user = "manatee";
		String pass = "Th3_hug3M4n4t33_str1k3s_4gA1N";
		System.out.println("Attempting to make a connection...");
		try (Connection conn = (Connection) DriverManager.getConnection(url, user, pass)) {
			System.out.println("Connection has been made.");
			Statement stmt = (Statement) conn.createStatement();
			String showUsers = "SELECT username, birthday FROM user;";
			ResultSet rs = stmt.executeQuery(showUsers);
			while (rs.next()) {
				String name = rs.getString("username");
				String birthday = rs.getString("birthday");
				System.out.println("User " + name + " was born on " + birthday);
			}
		} catch (Exception ex) {
			System.out.println("Connection failed...");
			System.out.println(ex);
		}
	}
}
