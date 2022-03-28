/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import java.sql.*;

/**
 *
 * @author gracy
 */
public class DatabaseDriver {

	protected Connection connection;
	protected Statement stmt;
	protected PreparedStatement prepStmt;
	protected ResultSet resultSet;

	public DatabaseDriver() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/tranoko", "root", "");
			stmt = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
