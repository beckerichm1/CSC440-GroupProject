package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SimilConnection {

	public static Connection connect() throws SQLException, ClassNotFoundException {
		String url = "jdbc:mysql://localhost:3306/simul_db";
		String user = "manatee";
		String pass = "Th3_hug3M4n4t33_str1k3s_4gA1N";
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(url, user, pass);
	}
}
