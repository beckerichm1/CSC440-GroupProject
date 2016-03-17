package database;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import utility.SimilConnection;

public class Account {
	// friend(user1, user2)
	public static boolean insertAccount(String userName, String fName, String lName, String email, Date birth, String pw) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		try {
			Connection conn = SimilConnection.connect();
			String query = "INSERT INTO user (userName, fName, lName, email, birthday, joined, userType, password, location)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			//System.out.println("User name: " + userName + " " + fName + " " + lName + " " + email + " " + birth);
			stmt.setString(1, userName);
			stmt.setString(2, fName);
			stmt.setString(3, lName);
			stmt.setString(4, email);
			stmt.setDate(5, birth);
			stmt.setString(6, dateFormat.format(cal.getTime()));
			stmt.setString(7, "Basic");
			stmt.setString(8, pw);
			stmt.setInt(9, 1);
			stmt.executeUpdate();
			conn.close();
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
		return true;
	}

	// Returns an arraylist of string with the first entry being a string of interests
	// and the rest of the entries being the user's panels
	public static ArrayList<String> getAccountDetails(String userName) {
		ArrayList<String> accountInfo = new ArrayList<>();
		try {
			Connection conn = SimilConnection.connect();

			// get interests
			String query = "select interests from User where userName = ?";			
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, userName);
			System.out.println(stmt);
			ResultSet rs = stmt.executeQuery();
			String interests = "";
			rs.next();
			// Only get interests once, as they repeat
			interests = rs.getString("interests");
			accountInfo.add(interests);


			// get panels
			query = "select panelName from Panel p JOIN Panel_Account pa where "
					+ "pa.panelID = p.panelID AND pa.userName = ?";			
			stmt = conn.prepareStatement(query);
			stmt.setString(1, userName);
			System.out.println(stmt);
			rs = stmt.executeQuery();
			while (rs.next()) {
				accountInfo.add(rs.getString("panelName"));
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
		return accountInfo;
	}


	public static String getPass(String userName) {
		String userPass= "";
		try {
			Connection conn = SimilConnection.connect();
			String query = "SELECT password FROM user WHERE userName = ? ;";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, userName);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				userPass = rs.getString("password");
			}
			conn.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return userPass;
	}
	
	public static String getAccountType(String userName){
		String accountType = "";
		try {
			Connection conn = SimilConnection.connect();
			String query = "SELECT userType FROM User WHERE userName = ? ;";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, userName);
			ResultSet rs = stmt.executeQuery();
			accountType = rs.getString("userType");
			conn.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return accountType;
	}
}

