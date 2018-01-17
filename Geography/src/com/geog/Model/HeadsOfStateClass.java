//Author:Kevin Delassus
//G00270791
//Heads of State Class
package com.geog.Model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped

public class HeadsOfStateClass {
	
	//Variables
	private String _id;
	private String headsOfState;
	
	//Constructor
	public HeadsOfStateClass() {
		
		super();

	}
	//Getters and Setters
	public String get_id() {
		
		return _id;
		
	}

	public void set_id(String _id) {
		
		this._id = _id;
		
	}

	public String getHeadsOfState() {
		
		return headsOfState;
		
	}

	public void setHeadsOfState(String headsOfState) {
		
		this.headsOfState = headsOfState;
		
	}
	
}
