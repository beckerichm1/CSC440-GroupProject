package database;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Account {
	// friend(user1, user2)
	public static boolean insertAccount(String userName, String fName, String lName, String email, Date birth, String pw) {
		String url = "jdbc:mysql://localhost:3306/simul_db";
		String user = "manatee";
		String pass = "Th3_hug3M4n4t33_str1k3s_4gA1N";
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, pass);

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
		String url = "jdbc:mysql://localhost:3306/simul_db";
		String user = "manatee";
		String pass = "Th3_hug3M4n4t33_str1k3s_4gA1N";
		try {
			System.out.println("in the db account java");
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, pass);

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
		String url = "jdbc:mysql://localhost:3306/simul_db";
		String user = "manatee";
		String pass = "Th3_hug3M4n4t33_str1k3s_4gA1N";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, pass);
			String query = "SELECT password FROM user WHERE userName = ? ;";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, userName);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				userPass = rs.getString("password");
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
		return userPass;
	}
	
	public static String getAccountType(String userName){
		String accountType = "";
		String url = "jdbc:mysql://localhost:3306/simul_db";
		String user = "manatee";
		String pass = "Th3_hug3M4n4t33_str1k3s_4gA1N";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, pass);
			String query = "SELECT userType FROM User WHERE userName = ? ;";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, userName);
			ResultSet rs = stmt.executeQuery();
			accountType = rs.getString("userType");
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return accountType;
	}
}

