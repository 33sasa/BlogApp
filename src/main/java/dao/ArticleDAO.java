package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.ArticleDTO;

public class ArticleDAO {
	// DB接続情報
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/blogdb";
	private static final String DB_USER = "root";
	private static final String DB_PASS = "";

	// 前記事の取得
	public List<ArticleDTO> findAll() {

		List<ArticleDTO> articleList = new ArrayList<>();

		// DB接続
		try {
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			// String sql = " USE BLOGDB; SELECT * FROM ARTICLE ";
			PreparedStatement pStmt = conn.prepareStatement("SELECT ID, TITLE, BODY, USER_NAME, DATE FROM ARTICLE");
			// SELECTの結果を取得
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				// レコードの取得
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String body = rs.getString("BODY");
				String user_name = rs.getString("USER_NAME");
				Date date = rs.getDate("DATE");
				ArticleDTO article = new ArticleDTO(id, title, body, user_name, date);

				articleList.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return articleList;
	}

	// 1記事の取得
	public List<ArticleDTO> searchArticle(int id) {

		List<ArticleDTO> articleList = new ArrayList<>();

		// DB接続
		try {
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			PreparedStatement pStmt = conn
					.prepareStatement("SELECT ID, TITLE, BODY, USER_NAME, DATE FROM ARTICLE WHERE ID = ? ");
			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				// レコードの取得
				int articleid = rs.getInt("ID");
				String title = rs.getString("TITLE");
				String body = rs.getString("BODY");
				String user_name = rs.getString("USER_NAME");
				Date date = rs.getDate("DATE");
				ArticleDTO article = new ArticleDTO(articleid, title, body, user_name, date);

				articleList.add(article);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return articleList;
	}

	// 記事の登録
	public List<ArticleDTO> articleSave(String title, String body, String user_name) {

		// DB接続
		try {
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			Date date = new Date();
			long timeInMilliSeconds = date.getTime();
			java.sql.Date today = new java.sql.Date(timeInMilliSeconds);

			PreparedStatement pStmt = conn
					.prepareStatement("INSERT INTO article (title, body, user_name, date) VALUES(?,?,?,?)");
			pStmt.setString(1, title);
			pStmt.setString(2, body);
			pStmt.setString(3, user_name);
			pStmt.setDate(4, today);
			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	// 記事の更新
	public List<ArticleDTO> updateArticle(int id, String title, String body, String user_name) {

		// DB接続
		try {
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			Date date = new Date();
			long timeInMilliSeconds = date.getTime();
			java.sql.Date today = new java.sql.Date(timeInMilliSeconds);

			PreparedStatement pStmt = conn.prepareStatement(
					"UPDATE ARTICLE SET ID = ?, TITLE = ?, BODY = ?, USER_NAME = ?, DATE = ? WHERE ID = ? ");
			pStmt.setInt(1, id);
			pStmt.setString(2, title);
			pStmt.setString(3, body);
			pStmt.setString(4, user_name);
			pStmt.setDate(5, today);
			pStmt.setInt(6, id);

			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	// 記事の削除
	public List<ArticleDTO> deleteArticle(int id) {

		// DB接続
		try {
			Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			PreparedStatement pStmt = conn.prepareStatement("DELETE FROM ARTICLE WHERE ID = ? ");
			pStmt.setInt(1, id);
			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
}
