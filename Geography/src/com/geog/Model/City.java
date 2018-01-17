//Author:Kevin Delassus
//G00270791
//City Class
package com.geog.Model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
@ManagedBean
@SessionScoped

public class City {
	
	//Variables
	private String cityCode;
	private String cityCtyCode;
	private String cityRegCode;
	private String cityName;
	private int cityPopul;
	private boolean cityIsCoastal;
	private double cityAreaKM;
	
	//Constructors
	public City() {
		
		super();
		
	}
	//Getters and Setters
	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityCtyCode() {
		return cityCtyCode;
	}

	public void setCityCtyCode(String cityCtyCode) {
		this.cityCtyCode = cityCtyCode;
	}

	public String getCityRegCode() {
		return cityRegCode;
	}

	public void setCityRegCode(String cityRegCode) {
		this.cityRegCode = cityRegCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public int getCityPopul() {
		return cityPopul;
	}

	public void setCityPopul(int cityPopul) {
		this.cityPopul = cityPopul;
	}

	public boolean isCityIsCoastal() {
		return cityIsCoastal;
	}

	public void setCityIsCoastal(boolean cityIsCoastal) {
		this.cityIsCoastal = cityIsCoastal;
	}

	public double getCityAreaKM() {
		return cityAreaKM;
	}

	public void setCityAreaKM(double cityAreaKM) {
		this.cityAreaKM = cityAreaKM;
	}
	
	

}
