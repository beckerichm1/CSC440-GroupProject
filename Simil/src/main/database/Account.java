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
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			String query = "INSERT INTO Account (userName, fName, lName, email, birthday, joined, userType, password)"
					+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, userName);
			stmt.setString(2, fName);
			stmt.setString(3, lName);
			stmt.setString(4, email);
			stmt.setDate(5, birth);
			stmt.setString(6, dateFormat.format(cal.getTime()));
			stmt.setString(7, "BASIC");
			stmt.setString(8, pw);
			stmt.executeUpdate(query);
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
		try (Connection conn = DriverManager.getConnection(url, user, pass)) {
			String query = "SELECT panelName, interests FROM Panel p JOIN Panel_Account pa JOIN User u"
					+ " p.panelID = pa.panelID AND pa.username = u.username AND u.username = ?;";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, userName);
			ResultSet rs = stmt.executeQuery(query);
			boolean firstSet = true;
			String interests = "";
			while (rs.next()) {
				// Only get interests once, as they repeat
				if(firstSet){
					interests = rs.getString("interests");
					accountInfo.add(interests);
					firstSet = false;
				}
				String panel = rs.getString("panelName");
				accountInfo.add(panel);
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return accountInfo;
	}
}
