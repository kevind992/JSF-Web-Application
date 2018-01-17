//Author:Kevin Delassus
//G00270791
//Country Controller Class
package com.geog.Controlller;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import com.geog.DAO.mySQLDAO;
import com.geog.Model.Country;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@ManagedBean
@ApplicationScoped

public class CountryController {
	
	//Variables
	private ArrayList<Country> countryArrayList ;
	private ArrayList<Country> updateArrayList;
	private mySQLDAO myDAO;
	
	public CountryController() {
		
		countryArrayList = new ArrayList<Country>();
		updateArrayList = new ArrayList<Country>();
		
		try {
			myDAO = new mySQLDAO();
		}catch(Exception e) {
			
			System.out.println("Countrys Error");
			FacesMessage message = new FacesMessage("Error: " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

	}
	//Get Method
	public void getCountryInfo() {
		
		System.out.println("inside getCountryInfo()");
		try {
			countryArrayList = myDAO.getCountryInfo();
			System.out.println("exiting getCountryInfo()");
		} catch (Exception e) {
			FacesMessage message = new FacesMessage("Error: Cannot connect to Database");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	//Add Method
	public String addCountryInfo(Country c) {
		
		System.out.println("inside setCountryInfo()");
		
		try {
			
			myDAO.addCounties(c);
			return "country.xhtml";
			
		}catch (MySQLIntegrityConstraintViolationException e) {
			FacesMessage message = new FacesMessage("Error: Country Code " + c.getCtyCode() + " already exists");
			FacesContext.getCurrentInstance().addMessage(null, message);
			System.out.println("exiting addCountryInfo()");
			return null;
		} catch (Exception e) {
			FacesMessage message = new FacesMessage("Error while trying to insert Country Code " + c.getCtyCode());
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
	}
	//Delete Method
	public String deleteCountryFROMDB(Country c) {
		
		System.out.println("inside deleteCountry()");
		try {
			myDAO.deleteFromDB("country", c.getCtyName());
			System.out.println("exiting deleteCountry()");
			return "country.xhtml";
		} catch (Exception e) {
			FacesMessage message = new FacesMessage("Error: " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
		
		
	}
	//Update Method
	public String updateCountryFROMDB(String ctyCode, String ctyName, String ctyDetails) {
		
		System.out.println("inside updateCountryInfo");
		try {
			myDAO.updateCountryDetails(ctyCode, ctyName, ctyDetails);
			System.out.println("exiting updateCountryInfo");
			return "country.xhtml";
		} catch (Exception e) {
			FacesMessage message = new FacesMessage("Error: " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
	}
	//Take Method
	public String takeUpdate(Country c) {
		System.out.println("in takeUpdate()");
		countryArrayList.clear();
		countryArrayList.add(c);
		System.out.println("exiting takeUpdate()");
		return "updateCountry.xhtml";
	}
	//Getters and Setters
	public ArrayList<Country> getCountryArrayList() {
		return countryArrayList;
	}

	public void setCountryArrayList(ArrayList<Country> countryArrayList) {
		this.countryArrayList = countryArrayList;
	}

	public mySQLDAO getMyDAO() {
		return myDAO;
	}

	public void setMyDAO(mySQLDAO myDAO) {
		this.myDAO = myDAO;
	}
	public ArrayList<Country> getUpdateArrayList() {
		return updateArrayList;
	}
	public void setUpdateArrayList(ArrayList<Country> updateArrayList) {
		this.updateArrayList = updateArrayList;
	}
	
}
