//Author:Kevin Delassus
//G00270791
//Country Class
package com.geog.Model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped

public class Country {
	
	//Variables
	private String ctyCode;
	private String ctyName;
	private String ctyDetails;
	
	//Constructor
	public Country() {
		super();
	}
	//Getters and Setters
	public String getCtyCode() {
		return ctyCode;
	}

	public void setCtyCode(String ctyCode) {
		this.ctyCode = ctyCode;
	}

	public String getCtyName() {
		return ctyName;
	}

	public void setCtyName(String ctyName) {
		this.ctyName = ctyName;
	}

	public String getCtyDetails() {
		return ctyDetails;
	}

	public void setCtyDetails(String ctyDetails) {
		this.ctyDetails = ctyDetails;
	}
}
