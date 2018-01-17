//Author: Kevin Delassus
//G00270791
//mongoDAO Class
package com.geog.DAO;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import com.geog.Model.City;
import com.geog.Model.HeadsOfStateClass;
import com.google.gson.Gson;

public class mongoDAO {
	
	private MongoClient mongoClient;
	private MongoDatabase database;
	private MongoCollection<Document> hosDB2;
	private Gson gson; 
	
	//Constructor
	public mongoDAO() {
		
		gson = new Gson();
		mongoClient = new MongoClient();
		database = mongoClient.getDatabase("headsOfStateDB");
		hosDB2 = database.getCollection("headsOfState");
		
	}
	
	//Get Method
	public ArrayList<HeadsOfStateClass> getHeadsOfStateInfo() {
		
		System.out.println("Fetching Into");
		
		ArrayList<HeadsOfStateClass> hofArraylist = new ArrayList<HeadsOfStateClass>();
		
		FindIterable<Document> hosDB = hosDB2.find();
		
		for(Document d : hosDB) {
			
			HeadsOfStateClass hos = gson.fromJson(d.toJson(), HeadsOfStateClass.class);
			hofArraylist.add(hos);
			
		}
		
		//mongoClient.close();
		System.out.println("info fetched and stored!");
		return hofArraylist;
	}
	
	//Add Method
	public void addHeadsOfState(HeadsOfStateClass hosc){
		System.out.println("adding data..");
		Document d = new Document();
		d.append("_id",hosc.get_id());
		d.append("headsOfState",hosc.getHeadsOfState());
		hosDB2.insertOne(d);
		System.out.println("data added..");
	}
	
	//Delete Method
	public void deleteHeadsOfState(HeadsOfStateClass hosc) {
		System.out.println("deleting data..");
		Document d = new Document();
		d.append("_id",hosc.get_id());
		d.append("headsOfState",hosc.getHeadsOfState());
		hosDB2.deleteOne(d);
		System.out.println("data deleted..");
	}

}
