/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package models;

import java.sql.SQLException;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import utilities.DatabaseDriver;
import utilities.SessionUtils;

/**
 *
 * @author gracy
 */
@Named(value = "purchase")
@Dependent
public class Purchase extends DatabaseDriver {
	
	public String buy(String houseID){
		String userID = SessionUtils.getID();
		try{
		prepStmt = connection.prepareStatement("insert into purchase values(?, ?, curdate())");
		prepStmt.setInt(1, Integer.valueOf(houseID));
		prepStmt.setInt(2, Integer.valueOf(userID));
		prepStmt.executeUpdate();
		
		prepStmt = connection.prepareStatement("update house set disponible=0 where houseID=?");
		prepStmt.setString(1, houseID);
		prepStmt.executeUpdate();
		}
		catch (SQLException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return "";
	}

}
