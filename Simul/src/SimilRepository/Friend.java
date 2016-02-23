package SimilRepository;

import java.sql.*;
import java.util.ArrayList;

public class Friend {
	// friend(user1, user2)
	public boolean insertFriend(String user1, String user2) {
		String url = "jdbc:mysql://localhost:3306/simul_db";
		String user = "manatee";
		String pass = "Th3_hug3M4n4t33_str1k3s_4gA1N";
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			String query = "INSERT INTO Friend VALUES(?, ?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, user1);
			stmt.setString(2, user2);
			stmt.executeUpdate(query);
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
		return true;
	}

	public boolean deleteFriend(String user1, String user2) {
		String url = "jdbc:mysql://localhost:3306/simul_db";
		String user = "manatee";
		String pass = "Th3_hug3M4n4t33_str1k3s_4gA1N";
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			String query = "DELETE FROM Friend WHERE userName1 = ? AND userName2 = ? OR"
					+ "userName2 = ? AND userName1 = ?;";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, user1);
			stmt.setString(2, user2);
			stmt.setString(3, user1);
			stmt.setString(4, user2);
			stmt.executeUpdate(query);
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
		return true;
	}

	public ArrayList<String> getAllFriends(String userName) {
		ArrayList<String> friends = new ArrayList<>();
		String url = "jdbc:mysql://localhost:3306/simul_db";
		String user = "manatee";
		String pass = "Th3_hug3M4n4t33_str1k3s_4gA1N";
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			String query = "SELECT * FROM Friend WHERE userName1 = ? OR userName2 = ?;";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, userName);
			stmt.setString(2, userName);
			ResultSet rs = stmt.executeQuery(query);
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
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return friends;
	}
}
