/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package entities;

import controllers.UserController;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

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
		
	}
	
	private String nom;
	private String prenom;
	private String email;
	private String password;
	
	// private PHOTO
	
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
	
	public String addUser(){
		return "";
	}
}
