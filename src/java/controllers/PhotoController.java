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
	
	public void AddPhoto(InputStream data){
		try{
			prepStmt = connection.prepareStatement("insert into photo(data) values(?)");
			prepStmt.setBinaryStream(1, data);
			prepStmt.executeUpdate();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
}
