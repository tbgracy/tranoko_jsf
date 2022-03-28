/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gracy
 */
public class DatabaseDriver {

	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	public DatabaseDriver() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/tranoko", "root", "");
			statement = connection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void AddPhoto(InputStream data){
		try{
			PreparedStatement stmt;
			stmt = connection.prepareStatement("insert into photo(data) values(?)");
			stmt.setBinaryStream(1, data);
			stmt.executeUpdate();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void Insert(String table, String data[]){
		try {
			PreparedStatement stmt;
			stmt = connection.prepareStatement("insert into house (ville, adresse, prix, categorie, descriptif) values (?, ?, ?, ?, ?)");
//			stmt.setString(1, table);
			for (int i=0; i<data.length; i++){
				stmt.setString(i+1, data[i]);
			}
			System.out.println(stmt);
			stmt.executeUpdate();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	public List<List<String>> ReadAll(String table) {
		String query = "select * from " + table;
		List<List<String>> res = new ArrayList();
		int i = 0;

		try {
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				res.add(new ArrayList());
				for (int j = 1; j < 9; j++) {
					res.get(i).add(resultSet.getString(j));
				}
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}
}
