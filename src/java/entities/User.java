/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package entities;

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
	
}
