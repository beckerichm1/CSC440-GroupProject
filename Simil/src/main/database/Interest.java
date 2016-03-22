package database;

import java.sql.*;
import java.util.ArrayList;

import utility.SimilConnection;

public class Interest {
	// This will need to be changed to exclude interests the user already has
	public static ArrayList<String> getAllInterests() {
		ArrayList<String> interests = new ArrayList<>();
		System.out.println("Attempting to make a connection...");
		try {
			Connection conn = SimilConnection.connect();
			System.out.println("Connection has been made.");
			Statement stmt = (Statement) conn.createStatement();
			String showInterests = "SELECT * FROM Interest;";
			ResultSet rs = stmt.executeQuery(showInterests);
			while (rs.next()) {
				String name = rs.getString("interestName");
				interests.add(name);
				// System.out.println("Interested User " + name);
			}
			System.out.println("Finished getting interests.");
			conn.close();
		} catch (Exception ex) {
			System.out.println("Connection failed...");
			System.out.println(ex);
		}
		return interests;
	}

	// friend(user1, user2)
	public boolean insertInterest(String interest) {
		try {
			Connection conn = SimilConnection.connect();
			String query = "INSERT INTO Interest VALUES(?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, interest);
			stmt.executeUpdate(query);
			conn.close();
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
		return true;
	}

	public boolean deleteInterest(String interest) {
		try {
			Connection conn = SimilConnection.connect();
			String query = "DELETE FROM Interest WHERE interestName = ?;";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, interest);
			stmt.executeUpdate(query);
			conn.close();
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
		return true;
	}

	public static boolean addInterests(String username, String[] interestsArray) {
		try {
			String interests = "";
			for (int i = 0; i < interestsArray.length; i++) {
				interests += (interestsArray[i] + "_");
			}
			Connection conn = SimilConnection.connect();
			String query = "UPDATE User SET interests = concat(IFNULL(interests,''), ?) WHERE userName = ?;";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, interests);
			stmt.setString(2, username);
			System.out.println(stmt);
			stmt.executeUpdate();
			conn.close();
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
		return true;
	}
	
	public static String getUserInterests(String userName) {
		String interests = "";
		try{
			Connection conn = SimilConnection.connect();
			// get interests
			String query = "select interests from User where userName = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, userName);
			System.out.println(stmt);
			ResultSet rs = stmt.executeQuery();
			rs.next();
			// Only get interests once, as they repeat
			interests = rs.getString("interests");
			conn.close();
		}catch(Exception ex){
			System.out.println(ex);;
			return null;
		}
		return interests;
	}

}
