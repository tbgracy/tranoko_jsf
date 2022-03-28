/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utilities.DatabaseDriver;

/**
 *
 * @author gracy
 */
public class HouseController extends DatabaseDriver {
	
	private List<List<String>> allHouses = new ArrayList();
	
	public List<List<String>> getAllHouses() {
		fetchHousesFromDb();
		return allHouses;
	}
	
	public void fetchHousesFromDb() {
		String query = "select * from house";
		int i = 0;
		
		try {
			resultSet = stmt.executeQuery(query);
			while (resultSet.next()) {
				allHouses.add(new ArrayList());
				for (int j = 1; j < 9; j++) {
					allHouses.get(i).add(resultSet.getString(j));
				}
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addHouse(String data[]) {
		try {
			prepStmt = connection.prepareStatement("insert into house (ville, adresse, prix, categorie, descriptif) values(?, ?, ?, ?, ?)");
			for (int i = 0; i < data.length; i++){
				prepStmt.setString(i+1, data[i]);
			}
			prepStmt.executeUpdate();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}
}
