package database;

import java.sql.*;
import java.util.ArrayList;

public class Interest {
	// This will need to be changed to exclude interests the user already has
		public static ArrayList<String> getAllInterests() {
			ArrayList<String> interests = new ArrayList<>();
			String url = "jdbc:mysql://localhost:3306/simul_db";
			String user = "manatee";
			String pass = "Th3_hug3M4n4t33_str1k3s_4gA1N";
			System.out.println("Attempting to make a connection...");
			try{//mysql-connector-java-5.1.38-bin
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = (Connection) DriverManager.getConnection(url, user, pass);
				System.out.println("Connection has been made.");
				Statement stmt = (Statement) conn.createStatement();
				String showInterests = "SELECT * FROM Interest;";
				ResultSet rs = stmt.executeQuery(showInterests);
				while (rs.next()) {
					String name = rs.getString("interestName");
					interests.add(name);
					//System.out.println("Interested User " + name);
				}
				System.out.println("Finished getting interests.");
			} catch (Exception ex) {
				System.out.println("Connection failed...");
				System.out.println(ex);
			}
			return interests;
		}
	
	
	// friend(user1, user2)
	public boolean insertInterest(String interest) {
		String url = "jdbc:mysql://localhost:3306/simul_db";
		String user = "manatee";
		String pass = "Th3_hug3M4n4t33_str1k3s_4gA1N";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, pass);
			String query = "INSERT INTO Interest VALUES(?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, interest);
			stmt.executeUpdate(query);
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
		return true;
	}

	public boolean deleteInterest(String interest) {
		String url = "jdbc:mysql://localhost:3306/simul_db";
		String user = "manatee";
		String pass = "Th3_hug3M4n4t33_str1k3s_4gA1N";
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			String query = "DELETE FROM Interest WHERE interestName = ?;";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, interest);
			stmt.executeUpdate(query);
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
		return true;
	}
	
	
}
