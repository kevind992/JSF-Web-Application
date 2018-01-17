//Author:Kevin Delassus
//G00270791
//City Search Class
package com.geog.Model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class CitySearch {
	
	//Variables
	private boolean bTS;
	private int popul;
	private String ctyCode;
	private String isthen;
	
	//Constructors
	public CitySearch() {
		
		super();
		
	}
    //Getters and Setters	
	public boolean isbTS() {
		
		return bTS;
		
	}

	public void setbTS(boolean bTS) {
		
		this.bTS = bTS;
		
	}

	public int getPopul() {
		
		return popul;
		
	}

	public void setPopul(int popul) {
		
		this.popul = popul;
		
	}

	public String getIsthen() {
		
		return isthen;
		
	}

	public void setIsthen(String isthen) {
		
		this.isthen = isthen;
		
	}

	public String getCtyCode() {
		
		return ctyCode;
		
	}

	public void setCtyCode(String ctyCode) {
		
		this.ctyCode = ctyCode;
		
	}
	
}
