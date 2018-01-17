//Author:Kevin Delassus
//G00270791
//Region Class
package com.geog.Model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
@ManagedBean
@SessionScoped

public class Region {
	
	//Variables
	private String rgnCtyCode;
	private String rgnCode;
	private String rgnName;
	private String rgnDesc;
	
	//Constructor
	public Region() {
		super();
	}
	//Getters and Setters
	public String getRgnCtyCode() {
		return rgnCtyCode;
	}

	public void setRgnCtyCode(String rgnCtyCode) {
		this.rgnCtyCode = rgnCtyCode;
	}

	public String getRgnCode() {
		return rgnCode;
	}

	public void setRgnCode(String rgnCode) {
		this.rgnCode = rgnCode;
	}

	public String getRgnName() {
		return rgnName;
	}

	public void setRgnName(String rgnName) {
		this.rgnName = rgnName;
	}

	public String getRgnDesc() {
		return rgnDesc;
	}

	public void setRgnDesc(String rgnDesc) {
		this.rgnDesc = rgnDesc;
	}
	
	
}
