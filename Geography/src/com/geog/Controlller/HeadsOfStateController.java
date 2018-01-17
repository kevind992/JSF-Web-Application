//Author:Kevin Delassus
//G00270791
//Head of State Controller Class
package com.geog.Controlller;

import java.util.ArrayList;
import com.geog.DAO.mongoDAO;
import com.geog.DAO.mySQLDAO;
import com.geog.Model.Country;
import com.geog.Model.HeadsOfStateClass;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped

public class HeadsOfStateController {
	
	//Variables
	private ArrayList<HeadsOfStateClass> headsArrayList ;
	private ArrayList<Country> checkList;
	private mongoDAO mongo;
	private boolean contains, containsM;
	
	public HeadsOfStateController() {
		
		super();
		
		headsArrayList = new ArrayList<HeadsOfStateClass>();
		checkList = new ArrayList<Country>();
		
		try {
			mongo = new mongoDAO();
		} catch (Exception e) {
			FacesMessage message = new FacesMessage("Error: Cannot connect to Database");
			FacesContext.getCurrentInstance().addMessage(null, message);	
		}

	}
	//Get Method
	public void getHeadsOfState() {
		
		System.out.println("In heads of State.");
		headsArrayList = mongo.getHeadsOfStateInfo();		
		System.out.println("Leaving heads of State.");
		
	}
	//Add Method
	public String addHeadsOfState(HeadsOfStateClass hosc) {
		
		System.out.println("inside addHeadsOfState");
		
		try{
			mySQLDAO mysql = new mySQLDAO();
			checkList = mysql.getCountryInfo();
			headsArrayList = mongo.getHeadsOfStateInfo();
		} catch (Exception e) {e.printStackTrace();}
		
		//Checking Country Db to see if file is already in DB
		for(Country c : checkList) {
			if(hosc.get_id().equals(c.getCtyCode())){
				contains = true;
				break;
			}else {
				contains = false;
			}
		}
		//Checking Mongo to see if the file that is added is already in the DB
		for(HeadsOfStateClass h : headsArrayList) {
			if(hosc.get_id().equals(h.get_id())){
				containsM = true;
				break;
			}else {
				containsM = false;
			}
		}
		//If contains is true and containsM is false then the file will be added to the db. Otherwise an error message will be show to the user.
		if(contains == true) {
			if(containsM == true){
				FacesMessage message = new FacesMessage("Error: Country " + hosc.get_id() + " already exists");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			}else{
				System.out.println("File being added to MongoDB..");
				mongo.addHeadsOfState(hosc);
			}
		}else {
			
			FacesMessage message = new FacesMessage("Error: Country " + hosc.get_id() + " not Found!");
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
		
		System.out.println("exiting addHeadsOfState");
		
		return "headsOfState.xhtml";
	}
	//Delete Method
	public String deleteHeadsOfState(HeadsOfStateClass hosc) {
		System.out.println("inside deleteHeadsOfState");
		mongo.deleteHeadsOfState(hosc);
		System.out.println("exiting deleteHeadsOfState");
		
		return "headsOfState.xhtml";
	}
	//Getters and Setters
	public ArrayList<HeadsOfStateClass> getHeadsArrayList() {
		return headsArrayList;
	}
	public void setHeadsArrayList(ArrayList<HeadsOfStateClass> headsArrayList) {
		this.headsArrayList = headsArrayList;
	}
	public mongoDAO getMongo() {
		return mongo;
	}
	public void setMongo(mongoDAO mongo) {
		this.mongo = mongo;
	}
	
}
