//Author:Kevin Delassus
//G00270791
//Search Class
package com.geog.Model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped

public class SearchClass {
	
	//Variables
	private String ctyCode;
	private String ctyName;
	private String ctry;
	private String rgn;
	private int cityPop;
	private boolean bTS;
	private double area;
	
	//Constructor
	public SearchClass() {
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

	public String getCtry() {
		return ctry;
	}

	public void setCtry(String ctry) {
		this.ctry = ctry;
	}

	public String getRgn() {
		return rgn;
	}

	public void setRgn(String rgn) {
		this.rgn = rgn;
	}

	public int getCityPop() {
		return cityPop;
	}

	public void setCityPop(int cityPop) {
		this.cityPop = cityPop;
	}

	public boolean isbTS() {
		return bTS;
	}

	public void setbTS(boolean bTS) {
		this.bTS = bTS;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}
}
