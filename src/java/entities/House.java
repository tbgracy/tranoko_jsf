/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package entities;

import controllers.HouseController;
import controllers.PhotoController;
import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.Part;

/**
 *
 * @author gracy
 */
@Named(value = "house")
@RequestScoped
public class House implements Serializable {

	/**
	 * Creates a new instance of House
	 */
	public House() {
		allHouses = hc.getAllHouses();
	}

	private String ville;
	private String adresse;
	private String prix;
	private String categorie;
	private String descriptif;

	private List<List> allHouses;

	private final HouseController hc = new HouseController();
	private final PhotoController pc = new PhotoController();

	private Part uploadedPhoto;

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getPrix() {
		return prix;
	}

	public void setPrix(String prix) {
		this.prix = prix;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getDescriptif() {
		return descriptif;
	}

	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

	public List<List> getAllHouses() {
		return allHouses;
	}

	public Part getUploadedPhoto() {
		return uploadedPhoto;
	}

	public void setUploadedPhoto(Part uploadedPhoto) {
		this.uploadedPhoto = uploadedPhoto;
	}

	public String addHouse() {
		String data[] = {ville, adresse, prix, categorie, descriptif};
		try{
		hc.addHouse(data, uploadedPhoto.getInputStream());
		}
		catch(IOException e){
			e.printStackTrace();
		}
//		allHouses = hc.getAllHouses();
		return "";
	}
	
	public String filterHouse(){
		return "";
	}

}
