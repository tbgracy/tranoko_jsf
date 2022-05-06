/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utilities.DatabaseDriver;

/**
 *
 * @author gracy
 */
public class UserController extends DatabaseDriver {

	private InputStream userImage;

	public InputStream getUserImage() {
		return userImage;
	}

	public void updateUserInfos(String id, List<String> data) {
		String query = "update user set nom=?, prenom=?, email=? where userID=?";
		System.out.println(data);
		try {
			prepStmt = connection.prepareStatement(query);
			prepStmt.setString(1, data.get(0));
			prepStmt.setString(2, data.get(1));
			prepStmt.setString(3, data.get(2));
			prepStmt.setString(4, id);

			prepStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<String> fetchUserInfos(String id) {
		List<String> userInfos = new ArrayList();
		try {
			resultSet = stmt.executeQuery("select nom, prenom, email, password from user where userID=" + id);
			if (resultSet.next()) {
				userInfos.add(resultSet.getString("nom"));
				userInfos.add(resultSet.getString("prenom"));
				userInfos.add(resultSet.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userInfos;
	}

	public int getUserIdFrom(String email, String password) {
		try {
			prepStmt = connection.prepareStatement("select userID from user where email=? and password=md5(?)");
			prepStmt.setString(1, email);
			prepStmt.setString(2, password);
			resultSet = prepStmt.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt("userID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public boolean checkUser(String email, String password) {
		try {
			prepStmt = connection.prepareStatement("select email, password from user where email=? and password=md5(?)");
			prepStmt.setString(1, email);
			prepStmt.setString(2, password);
			resultSet = prepStmt.executeQuery();
			if (resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void photo(String id) {
		String query = "select photo from user where userID=" + id;
		try {
			resultSet = stmt.executeQuery(query);
			if (resultSet.next()) {
				userImage = resultSet.getBinaryStream("photo");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addUser(String data[], InputStream photo) {
		try {
			prepStmt = connection.prepareStatement("insert into user(nom, prenom, email, password, photo) values (?, ?, ?, md5(?), ?)");
			for (int i = 0; i < data.length; i++) {
				prepStmt.setString(i + 1, data[i]);
			}
			prepStmt.setBinaryStream(5, photo);
			prepStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
