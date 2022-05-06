/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package models;

import controllers.UserController;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.servlet.http.Part;
import utilities.SessionUtils;

/**
 *
 * @author gracy
 */
@Named(value = "user")
@SessionScoped
public class User implements Serializable {

	/**
	 * Creates a new instance of User
	 */
	public User() {
		userInfos = uc.fetchUserInfos(String.valueOf(getCurrentUserID()));

	}

	public String updateUser() {
//		this.userInfos = uc.fetchUserInfos(String.valueOf(getCurrentUserID()));
		uc.updateUserInfos(String.valueOf(getCurrentUserID()), userInfos);
		return "";
	}

	private List<String> userInfos;

	public List<String> getUserInfos() {
//		return userInfos;
		return uc.fetchUserInfos(String.valueOf(getCurrentUserID()));
	}

	public void setUserInfos(List<String> userInfos) {
		this.userInfos = userInfos;
	}

	public int getCurrentUserID() {
		return Integer.valueOf(SessionUtils.getID());
	}

	private String nom;
	private String prenom;
	private String email;
	private String password;

	private Part photo;

	public Part getPhoto() {
		return photo;
	}

	public void setPhoto(Part photo) {
		this.photo = photo;
	}

	private final UserController uc = new UserController();

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String addUser() {
		String data[] = {nom, prenom, email, password};
		try {
			uc.addUser(data, photo.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return "login";
	}
}
