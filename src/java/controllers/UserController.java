/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.io.InputStream;
import java.sql.SQLException;
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
			if (resultSet.next()){
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
