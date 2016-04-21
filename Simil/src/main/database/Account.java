package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import utility.SimilConnection;

public class Account {

	private String userName, about, fName, lName, userType, interests, email;
	private Date birthday, joined;


	// friend(user1, user2)
	public static boolean insertAccount(String userName, String fName, String lName, String email, Date birth,
			String pw) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		try {
			Connection conn = SimilConnection.connect();
			String query = "INSERT INTO user (userName, fName, lName, email, birthday, joined, userType, password, location)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement stmt = conn.prepareStatement(query);
			// System.out.println("User name: " + userName + " " + fName + " " +
			// lName + " " + email + " " + birth);
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

	// Returns an arraylist of string with the first entry being a string of
	// interests
	// and the rest of the entries being the user's panels
	public static ArrayList<String> getAccountDetails(String userName) {
		ArrayList<String> accountInfo = new ArrayList<String>();
		try {
			Connection conn = SimilConnection.connect();

			// get interests
			String query = "select interests from User where userName = ?";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, userName);
			ResultSet rs = stmt.executeQuery();
			String interests = "";
			rs.next();
			// Only get interests once, as they repeat
			interests = rs.getString("interests");
			if(interests != null)
				accountInfo.add(interests);

			//System.out.println(accountInfo);

			// get panels
			query = "select panelName from Panel p JOIN Panel_Account pa where "
					+ "pa.panelID = p.panelID AND pa.userName = ?";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, userName);
			rs = stmt.executeQuery();
			String panel;
			while (rs.next()) {
				panel = rs.getString("panelName");
				if(!panel.equals("null"))
					accountInfo.add(panel);
			}
			//accountInfo.addAll(Panel.getUserPanels(userName));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return accountInfo;
	}

	public static String getPass(String userName) {
		String userPass = "";
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

	public static String getAccountType(String userName) {
		String accountType = "";
		try {
			Connection conn = SimilConnection.connect();
			String query = "SELECT userType FROM User WHERE userName = ? ;";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, userName);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				accountType = rs.getString("userType");
			}
			conn.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		System.out.println("MY ACCOUNT:" + accountType);
		return accountType;
	}

	public static boolean updatePass(String userName, String password) {
		try {
			Connection conn = SimilConnection.connect();
			String query = "UPDATE user SET Password = ? where userName = ?;";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, password);
			stmt.setString(2, userName);
			stmt.executeUpdate();
			System.out.println(stmt);
			conn.close();
		} catch (Exception ex) {
			System.out.println(ex);
			return false;
		}
		return true;
	}

	public static ArrayList<String> getAccountsLike(String id) {
		ArrayList<String> users = new ArrayList<>();
		try{
			Connection conn = SimilConnection.connect();
			String query = "SELECT userName FROM user where fName LIKE ? OR lName LIKE ? OR userName LIKE ?;";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, id + "%");
			stmt.setString(2, id + "%");
			stmt.setString(3, id + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String entry = rs.getString("userName");
				users.add(entry);
			}
			conn.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return users;
	}
	
	
	
	
	
	public Account(String userName, String fName, String lName, String about, Date birth, String interests){
		this.userName = userName;
		this.fName = fName;
		this.lName = lName;
		this.about = about;
		this.interests = interests;
	}

	public String getUserName(){
		return userName;
	}
	public String getFName(){
		return fName;
	}
	public String getLName(){
		return lName;
	}
	public String getEmail(){
		return email;
	}
	public Date getBirthday(){
		return birthday;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}
	public void setFName(String fName){
		this.fName = fName;
	}
	public void setLName(String lName){
		this.lName = lName;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public void SetBirthday(Date birthday){
		this.birthday = birthday;
	}

	public static String getAbout(String id) {
		String about = "";
		try {
			Connection conn = SimilConnection.connect();
			String query = "SELECT about FROM User WHERE userName = ? ;";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				about = rs.getString("about");
			}
			conn.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return about;
	}
}
