/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package models;

import controllers.UserController;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import utilities.SessionUtils;

/**
 *
 * @author gracy
 */
@Named(value = "login")
@SessionScoped
public class Login implements Serializable{

	/**
	 * Creates a new instance of Login
	 */
	public Login() {
	}

	private UserController uc = new UserController();

	private String email;
	private String password;
	private String message;

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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String validateLogin() {
		boolean validUser = uc.checkUser(email, password);
		if (validUser) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("email", email);
			return "index";
		} else {
			FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(
					FacesMessage.SEVERITY_WARN,
					"Incorrect email or password",
					"Please enter correct email and password"));
			return "login";
		}
	}

	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login";
	}
}
