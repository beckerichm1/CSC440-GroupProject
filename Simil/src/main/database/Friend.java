package database;

import java.sql.*;
import java.util.ArrayList;

import utility.SimilConnection;

public class Friend {
	// friend(user1, user2)
	public boolean insertFriend(String user1, String user2) {
		try {
			Connection conn = SimilConnection.connect();
			String query = "INSERT INTO Friend VALUES(?, ?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, user1);
			stmt.setString(2, user2);
			stmt.executeUpdate(query);
			conn.close();
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
		return true;
	}

	public boolean deleteFriend(String user1, String user2) {
		try {
			Connection conn = SimilConnection.connect();
			String query = "DELETE FROM Friend WHERE userName1 = ? AND userName2 = ? OR"
					+ "userName2 = ? AND userName1 = ?;";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, user1);
			stmt.setString(2, user2);
			stmt.setString(3, user1);
			stmt.setString(4, user2);
			stmt.executeUpdate(query);
			conn.close();
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
		return true;
	}

	public static ArrayList<String> getAllFriends(String userName) {
		ArrayList<String> friends = new ArrayList<>();
		try {
			Connection conn = SimilConnection.connect();
			String query = "SELECT * FROM Friend WHERE userName1 = ? OR userName2 = ?;";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, userName);
			stmt.setString(2, userName);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// Get the two user names in each friend entry
				String user1 = rs.getString("userName1");
				String user2 = rs.getString("userName2");
				// Don't add the queried user (for whom we're listing friends)
				// Only add people to the list that AREN'T the queried user
				if (!user1.equals(userName))
					friends.add(user1);
				if (!user2.equals(userName))
					friends.add(user2);
			}
			conn.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return friends;
	}
}
