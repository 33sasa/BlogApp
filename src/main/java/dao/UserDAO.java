package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.UserDTO;

public class UserDAO {

	// DB接続情報
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/blogdb";
	private static final String DB_USER = "root";
	private static final String DB_PASS = "";

	public boolean findbyLogin(UserDTO user) {

		// DB接続
		try {
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			PreparedStatement pStmt = conn
					.prepareStatement("SELECT NAME, PASSWORD FROM USER WHERE NAME = ? AND PASSWORD = ?");
			// SELECTの結果を取得
			pStmt.setString(1, user.getName());
			pStmt.setString(2, user.getPassword());
			pStmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
