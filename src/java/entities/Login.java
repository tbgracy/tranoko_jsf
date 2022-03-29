/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package entities;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author gracy
 */
@Named(value = "login")
@RequestScoped
public class Login {

	/**
	 * Creates a new instance of Login
	 */
	public Login() {
	}
	
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String validateLogin(){
		return "";
	}
}
