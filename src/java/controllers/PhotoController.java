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
public class PhotoController extends DatabaseDriver{
	
	public void AddPhoto(InputStream data, String proprio, int proprioID){
		String sqlQuery = "insert into photo(data, " + proprio + "ID) values(?, ?)";
		try{
//			prepStmt = connection.prepareStatement("insert into photo(data) values(?)");
			prepStmt = connection.prepareStatement(sqlQuery);
			prepStmt.setBinaryStream(1, data);
			prepStmt.setInt(2, proprioID);
			prepStmt.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
}
