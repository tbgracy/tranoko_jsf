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
import utilities.SessionUtils;

/**
 *
 * @author gracy
 */
public class HouseController extends DatabaseDriver {

	private List<List> allHouses = new ArrayList();

	private InputStream houseImage;
	
	public List<List> getAllHouses() {
		fetchHousesFromDb();
		return allHouses;
	}
	
	public InputStream getHouseImage(){
		return houseImage;
	}
	
	public void filterHouses(String data[]){
		
	}

	public void photo(String id) {
		String query = "select photo from house where houseID=" + id;
		try {
			resultSet = stmt.executeQuery(query);
			if (resultSet.next()){
				houseImage = resultSet.getBinaryStream("photo");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
				allHouses.get(i).add(resultSet.getBinaryStream("photo"));
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			e.getMessage();
		}
	}

	public void addHouse(String data[], InputStream photo) {
		try {
			prepStmt = connection.prepareStatement("insert into house (ville, adresse, prix, categorie, descriptif, photo, userID) values(?, ?, ?, ?, ?, ?, ?)");
			for (int i = 0; i < data.length; i++) {
				prepStmt.setString(i + 1, data[i]);
			}
			prepStmt.setBinaryStream(6, photo);
			prepStmt.setInt(7, Integer.valueOf(SessionUtils.getID()));
			prepStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
