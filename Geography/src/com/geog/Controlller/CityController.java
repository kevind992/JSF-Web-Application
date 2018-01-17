//Author:Kevin Delassus
//G00270791
//City Controller Class

package com.geog.Controlller;

import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import com.geog.DAO.mySQLDAO;
import com.geog.Model.City;
import com.geog.Model.CitySearch;
import com.geog.Model.Country;
import com.geog.Model.Region;
import com.geog.Model.SearchClass;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@ManagedBean
@SessionScoped

public class CityController {
	
	//Variables and Array Lists
	private ArrayList<City> cityArrayList ;
	private ArrayList<Country> countryArrayList ;
	private ArrayList<Region> regionArrayList ;
	private ArrayList<SearchClass> searchArray;
	private mySQLDAO myDAO;
	private boolean contains1, contains2;
	
	public CityController() {
		
		super();
		
		cityArrayList = new ArrayList<City>();
		countryArrayList = new ArrayList<Country>();
		regionArrayList = new ArrayList<Region>();
		searchArray = new ArrayList<SearchClass>();
		
		try {
			myDAO = new mySQLDAO();
		} catch (Exception e) {
			FacesMessage message = new FacesMessage("Error: " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	//Get Method
	public void getCityInfo() {
		
		System.out.println("inside getCityInfo");
		try {
			cityArrayList = myDAO.getCityInfo();
			System.out.println("exiting getCityInfo");
		}catch (Exception e) {
			FacesMessage message = new FacesMessage("Error: Cannot connect to Database");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	//Add Method
	public String addCityInfo(City c) {
		
		System.out.println("inside addCityInfo");
		try{
			
			System.out.println("Filling Country and Region ArrayList..");
			//getting table information for region and country
			countryArrayList = myDAO.getCountryCode();
			regionArrayList = myDAO.getRegionCode();
			System.out.println("Country and Region ArrayList filled..");
			
			//Checking to see if what the user entered for country is in the country table
			for(Country ct : countryArrayList) {
				if(c.getCityCtyCode().equals(ct.getCtyCode())){
					contains1 = true;
					break;
				}else {
					contains1 = false;
				}
			}
			//Checking to see if what the user entered for Region code is in the country table
			for(Region rn :  regionArrayList) {
				if(c.getCityRegCode().equals(rn.getRgnCode())){
					contains2 = true;
					break;
				}else{
					contains2 = false;
				}
			}
			//If both are true then the info is added to the table otherwise an error is sent to the user.
			if(contains1 == true) {
				if(contains2 == true) {
					myDAO.addCities(c);
					System.out.println("Country will  be added");
					System.out.println("exiting addCityInfo");
					return "cities.xhtml";
				}else{
					FacesMessage message = new FacesMessage("Error: Region Code '" + c.getCityRegCode() + "' does not match with a Region Code in Country Table");
					FacesContext.getCurrentInstance().addMessage(null, message);
					return null;
				}
			}else{
				
				FacesMessage message = new FacesMessage("Error: Country Code '" + c.getCityCtyCode() + "' does not match with a Country Code in Country Table");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
				
			}	
		}catch (MySQLIntegrityConstraintViolationException e) {
			FacesMessage message = new FacesMessage("Error: City Code " + c.getCityCode() + " already exists");
			FacesContext.getCurrentInstance().addMessage(null, message);
			System.out.println("exiting addCountryInfo()");
			return null;
				
		} catch (Exception e) {
			
			FacesMessage message = new FacesMessage("Error while trying to insert City Code " + c.getCityCode());
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
			
		}
	}
	//Search Method
	public String searchCities(CitySearch cs) {
		
		System.out.println("Inside searchCities.");
		try {
			searchArray = myDAO.searchDB(cs.getCtyCode(),cs.getPopul(),cs.getIsthen(),cs.isbTS());
			System.out.println("Exiting searchCities.");
			return "findCitiesResults.xhtml";
		}catch (Exception e) {
			FacesMessage message = new FacesMessage("Error: " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
	}
	//getting full details
	public String fullDetails(City c) {
		
		System.out.println("inside fullDetails()");
		try {
			searchArray = myDAO.getDetails(c.getCityCode());
			System.out.println("exiting fullDetails()");
			return "fullDetails.xhtml";
		}catch (Exception e) {
			FacesMessage message = new FacesMessage("Error: " + e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
	}
	//Getters and Setters
	public ArrayList<City> getCityArrayList() {
		return cityArrayList;
	}
	public void setCityArrayList(ArrayList<City> cityArrayList) {
		this.cityArrayList = cityArrayList;
	}
	public mySQLDAO getMyDAO() {
		return myDAO;
	}
	public void setMyDAO(mySQLDAO myDAO) {
		this.myDAO = myDAO;
	}

	public ArrayList<SearchClass> getSearchArray() {
		return searchArray;
	}

	public void setSearchArray(ArrayList<SearchClass> searchArray) {
		this.searchArray = searchArray;
	}
}