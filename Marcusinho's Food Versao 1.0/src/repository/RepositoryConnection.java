package repository;

import java.sql.*;
import javax.swing.JOptionPane;

public class RepositoryConnection {

	private final String DRIVER = "com.mysql.jdbc.Driver";
	private final String URL = "jdbc:mysql://localhost:3306/marcuzinfood?allowMultiQueries=true";
	private final String USER = "root";
	private final String PASS = "";
	
	public Connection connect() throws Exception{

		Connection conn = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASS);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "RepositoryConnection - " + e.getMessage());
		}

		return conn;
	}

}
