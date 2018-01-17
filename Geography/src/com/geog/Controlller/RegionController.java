//Author:Kevin Delassus
//G00270791
//Region Controller Class
package com.geog.Controlller;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.geog.DAO.mySQLDAO;
import com.geog.Model.Country;
import com.geog.Model.Region;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@ManagedBean
@SessionScoped

public class RegionController {
	
	//Variables
	private ArrayList<Region> regionArrayList ;
	private ArrayList<Country> countryArrayList ;
	private mySQLDAO myDAO;
	private boolean contains;
	
	public RegionController() {
		
		super();
		
		regionArrayList = new ArrayList<Region>();
		countryArrayList = new ArrayList<Country>();
		
		try {
			myDAO = new mySQLDAO();
		} catch (Exception e) {
			FacesMessage message = new FacesMessage("Error: " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	//Add Method
	public String addRegionInfo(Region r) {
		
		System.out.println("inside addRegionInfo()");
		
		try {
			countryArrayList = myDAO.getCountryCode();
			
			for(Country ct : countryArrayList) {
				if(r.getRgnCtyCode().equals(ct.getCtyCode())){
					contains = true;
					break;
				}else {
					
					contains = false;
				}
			}
			if(contains == true) {
				myDAO.addRegions(r);
				System.out.println("exiting addRegionInfo()");
				return "regions.xhtml";
			}else {
				FacesMessage message = new FacesMessage("Error: Country '" +  r.getRgnCtyCode() + "' does not exist!");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			}
			
		}catch (MySQLIntegrityConstraintViolationException e) {
			
			FacesMessage message = new FacesMessage("Error: Region Code " + r.getRgnCode() + " already exists");
			FacesContext.getCurrentInstance().addMessage(null, message);
			System.out.println("exiting addCountryInfo()");
			return null;
				
		} catch (Exception e) {
			
			FacesMessage message = new FacesMessage("Error while trying to insert Region Code " + r.getRgnCode());
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
			
		}
	}
	//Get Method
	public void getRegionInfo() {
		
		System.out.println("inside getRegionInfo");
		try {
			regionArrayList = myDAO.getRegionInfo();
			System.out.println("exiting getRegionInfo");
		}catch (Exception e) {
			FacesMessage message = new FacesMessage("Error: Cannot connect to Database");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	//Getters and Setters
	public ArrayList<Region> getRegionArrayList() {
		return regionArrayList;
	}

	public void setRegionArrayList(ArrayList<Region> regionArrayList) {
		this.regionArrayList = regionArrayList;
	}

	public mySQLDAO getMyDAO() {
		return myDAO;
	}

	public void setMyDAO(mySQLDAO myDAO) {
		this.myDAO = myDAO;
	}
}
