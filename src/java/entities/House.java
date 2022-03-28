/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package entities;

import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import utilities.DatabaseDriver;

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
		houses = db.ReadAll("house");
		System.out.println(houses);
	}

	private String ville;
	private String adresse;
	private String prix;
	private String categorie;
	private String descriptif;

	private Part uploadedPhoto;
	private byte[] photo;

	public Part getUploadedPhoto() {
		return uploadedPhoto;
	}

	public void reload() throws IOException {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
	}

	public void setUploadedPhoto(Part uploadedPhoto) {
		this.uploadedPhoto = uploadedPhoto;
	}

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

	public String insertHouse() {
		String data[] = {ville, adresse, prix, categorie, descriptif};
		db.Insert("house", data);
		try {
			InputStream input = uploadedPhoto.getInputStream();
			db.AddPhoto(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	;
	
	private List<List<String>> houses;
	private final DatabaseDriver db = new DatabaseDriver();

	public List<List<String>> getHouses() {
		return houses;
	}

	public void setHouses(List<List<String>> houses) {
		this.houses = houses;
	}

}
