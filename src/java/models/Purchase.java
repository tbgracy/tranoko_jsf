/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package models;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
	
	private List<String> dates = new ArrayList();
	private List<Integer> sells = new ArrayList();
	private String time;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
		System.out.println(time);
		fetchSellsBy(time);
		System.out.println(dates);
		System.out.println(sells);
	}

	public List<String> getDates() {
		return dates;
	}

	public List<Integer> getSells() {
		return sells;
	}
	
	// Constructor
	public Purchase(){
		fetchSellsBy("day");
	}
	
	public void fetchSellsBy(String time){
		dates = new ArrayList();
		sells = new ArrayList();
		String query;
		switch (time){
			case "day":
				query = "select date(date) as date, count(*) as nombre_achat from purchase group by date(date)";
				break;
			case "month":
				query = "select monthname(date) as date, count(*) as nombre_achat from purchase group by month(date)";
				break;
			case "year":
				query = "";
				break;
			default:
				query = "";
				break;
		}
		try{
			resultSet = stmt.executeQuery(query);
			while (resultSet.next()){
				dates.add("\""+resultSet.getString("date")+"\"");
				sells.add(resultSet.getInt("nombre_achat"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public String buy(String houseID){
		String userID = SessionUtils.getID();
		try{
		prepStmt = connection.prepareStatement("insert into purchase values(?, ?, curdate())");
		prepStmt.setInt(1, Integer.valueOf(houseID));
		prepStmt.setInt(2, Integer.valueOf(userID));
		prepStmt.executeUpdate();
		
		prepStmt = connection.prepareStatement("update house set disponibilite=0 where houseID=?");
		prepStmt.setInt(1, Integer.valueOf(houseID));
		prepStmt.executeUpdate();
		}
		catch (SQLException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return "";
	}

}
