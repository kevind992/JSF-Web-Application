//Author: Kevin Delassus
//G00270791
//mySQLDAO Class
package com.geog.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import com.geog.Model.City;
import com.geog.Model.Country;
import com.geog.Model.Region;
import com.geog.Model.SearchClass;

public class mySQLDAO {
	
private DataSource mysqlDS;
	
	//Constructor
	public mySQLDAO() throws Exception{
		
		Context context = new InitialContext();
		String jndiName = "java:comp/env/jdbc/geography";
		mysqlDS = (DataSource) context.lookup(jndiName);
	}
	
	//Database get methods
	public ArrayList<City> getCityInfo() throws Exception {
		
		ArrayList<City> cityArraylist = new ArrayList<City>();
		
		System.out.println("Connected to getCityInfo()");
		Connection conn = mysqlDS.getConnection();
		Statement myStmt = conn.createStatement();
		
		String query = "select * from city";
		
		ResultSet rs = myStmt.executeQuery(query);
		System.out.println("Fetching Information");

		while( rs.next() ) {
			
			City c = new City();
			
			c.setCityCode(rs.getString("cty_code"));
			c.setCityCtyCode(rs.getString("co_code"));
			c.setCityRegCode(rs.getString("reg_code"));
			c.setCityName(rs.getString("cty_name"));
			c.setCityPopul(rs.getInt("population"));
			c.setCityIsCoastal(rs.getBoolean("isCoastal"));
			c.setCityAreaKM(rs.getDouble("areaKM"));
			
			cityArraylist.add(c);
				
		}

		System.out.println("Information fetched and stored!");
		return cityArraylist;
		
	}
	public ArrayList<Country> getCountryCode() throws Exception {
		System.out.println("inside getCountryCode()");
		ArrayList<Country>cCAL = new ArrayList<Country>();
		Connection conn = mysqlDS.getConnection();
		Statement myStmt = conn.createStatement();
		String query = "select * from country";
		ResultSet rs = myStmt.executeQuery(query);
		
		while( rs.next() ) {
			
			Country c = new Country();
			c.setCtyCode(rs.getString("co_code"));
			cCAL.add(c);
		}
		System.out.println("exiting getCountryCode()");
		return cCAL;
	}
	public ArrayList<SearchClass> getDetails(String cityCode) throws Exception {
		
		ArrayList<SearchClass> citySearchArraylist = new ArrayList<SearchClass>();
		
		System.out.println("Connected to getDetails()");
		Connection conn = mysqlDS.getConnection();
		Statement myStmt = conn.createStatement();
		String query = "select c.cty_code, c.cty_name, ct.co_name, r.reg_name, c.population,"
				+ " c.isCoastal, c.areaKM from city c inner join region r on c.reg_code = r.reg_code "
				+ "inner join country ct on c.co_code = ct.co_code where c.cty_code like '" + cityCode + "'";
		
		ResultSet rs = myStmt.executeQuery(query);
		System.out.println("Fetching Information");

		while(rs.next()) {
			
			SearchClass sc = new SearchClass();
			
			sc.setCtyCode(rs.getString("cty_code"));
			sc.setCtyName(rs.getString("cty_name"));
			sc.setCtry(rs.getString("co_name"));
			sc.setRgn(rs.getString("reg_name"));
			sc.setCityPop(rs.getInt("population"));
			sc.setbTS(rs.getBoolean("isCoastal"));
			sc.setArea(rs.getDouble("areaKM"));
			
			citySearchArraylist.add(sc);
		}
			
		System.out.println("Information fetched and stored!");
		
		return citySearchArraylist;
	}
	public ArrayList<Region> getRegionCode() throws Exception {
		
		System.out.println("inside getRegionCode()");
		
		ArrayList<Region> rCAL = new ArrayList<Region>();
		Connection conn = mysqlDS.getConnection();
		Statement myStmt = conn.createStatement();
		String query = "select * from region";
		ResultSet rs = myStmt.executeQuery(query);
		
		while( rs.next() ) {
			Region r = new Region();
			r.setRgnCode(rs.getString("reg_code"));
			rCAL.add(r);
		}
		System.out.println("exiting getRegionCode()");
		return rCAL;
	}
	public ArrayList<Country> getCountryInfo() throws Exception {
		
		ArrayList<Country> countryArraylist = new ArrayList<Country>();
				
		System.out.println("Connected to getCountryInfo()");
		Connection conn = mysqlDS.getConnection();
		Statement myStmt = conn.createStatement();
		String query = "select * from country";
		ResultSet rs = myStmt.executeQuery(query);
		
		System.out.println("Fetching Information");
		
		while(rs.next()) {
			
			Country c = new Country();
			c.setCtyCode(rs.getString("co_code"));
			c.setCtyName(rs.getString("co_name"));
			c.setCtyDetails(rs.getString("co_details"));
			
			countryArraylist.add(c);
		}
			
		System.out.println("Information fetched and stored!");
		
		return countryArraylist;
	}	
	public ArrayList<Region> getRegionInfo() throws Exception {
		
		ArrayList<Region> regionArraylist = new ArrayList<Region>();

		System.out.println("Connected to getCountryInfo()");
		Connection conn = mysqlDS.getConnection();
		Statement myStmt = conn.createStatement();
		String query = "select * from region";
		ResultSet rs = myStmt.executeQuery(query);
		System.out.println("Fetching Information");

		while(rs.next()) {
			
			Region r = new Region();
			r.setRgnCtyCode(rs.getString("co_code"));
			r.setRgnCode(rs.getString("reg_code"));
			r.setRgnName(rs.getString("reg_name"));
			r.setRgnDesc(rs.getString("reg_desc"));
			
			regionArraylist.add(r);
		}
			
		System.out.println("Information fetched and stored!");
		
		return regionArraylist;
	}
	
	//Database update methods
	public void addCounties(Country c) throws Exception{
		
		System.out.println("Connected to updateCounties");
		
		Connection conn = mysqlDS.getConnection();
		Statement myStmt = conn.createStatement();
		String query = "insert into country values(" + "'" + c.getCtyCode() + "', " + "'" + c.getCtyName() + "', " 
				+ "'" + c.getCtyDetails() + "')";
		myStmt.executeUpdate(query);
		System.out.println("Info added!");
			
	}
	public void addRegions(Region r) throws Exception {
		
		System.out.println("Connected to updateRegions");
		
		Connection conn = mysqlDS.getConnection();
		Statement myStmt = conn.createStatement();
		String query = "insert into region values(" + "'" + r.getRgnCtyCode() + "', " + "'" + r.getRgnCode() + "', " 
				+ "'" + r.getRgnName() + "', " + "'" + r.getRgnDesc() + "')";
		myStmt.executeUpdate(query);
		
		System.out.println("Info added!");
			
	}
	public void addCities(City c) throws Exception{
		
		System.out.println("Connected to updateCities");
		
		Connection conn = mysqlDS.getConnection();
		Statement myStmt = conn.createStatement();
		String query = "insert into city values(" + "'" + c.getCityCode() + "', " + "'" + c.getCityCtyCode() + "', " 
				+ "'" + c.getCityRegCode() + "', " + "'" + c.getCityName() + "', " + "'" + c.getCityPopul() +
				"', " + "'" + c.isCityIsCoastal() + "', " + "'" + c.getCityAreaKM() + "')";
		
		myStmt.executeUpdate(query);
		System.out.println("Info added!");
			
	}
	public void updateCountryDetails(String ctyCode, String ctyName, String ctyDetails) throws Exception {
		
		System.out.println("Connected to updateCounties");
		
		Connection conn = mysqlDS.getConnection();
		Statement myStmt = conn.createStatement();
		String query = "Update country set co_name = '" + ctyName + "' , co_details = '" + ctyDetails + "' where co_code like '" + ctyCode + "'";
				
		myStmt.executeUpdate(query);
		System.out.println("Info Updated!");
			
	}
	
	//Delete Method
	public void deleteFromDB(String table, String name)throws Exception {
		
		System.out.println("Connected to deleteFromDB");
		
		Connection conn = mysqlDS.getConnection();
		Statement myStmt = conn.createStatement();
		
		if(table.equals("country")) {
			
			String query = "delete from " + table + " where co_name like '" + name + "'";
			myStmt.executeUpdate(query);
			
		}
	
		System.out.println("Info Deleted!");
	}
	
	//Search Method
	public ArrayList<SearchClass> searchDB(String ctyCode, int pop, String gel, boolean bts) throws Exception{
		
		ArrayList<SearchClass> citySearchArraylist = new ArrayList<SearchClass>();
		
		String query;
		System.out.println("Connected to searchDB()");
		Connection conn = mysqlDS.getConnection();
		Statement myStmt = conn.createStatement();
		String statement = "select c.cty_code, c.cty_name, ct.co_name, r.reg_name, c.population, c.isCoastal, c.areaKM from city c inner join region r on c.reg_code = r.reg_code inner join country ct on c.co_code = ct.co_code";
		
		if(pop != 0/*population is true*/){
			
			System.out.println("If population is True");
			
			if(!ctyCode.equals("")) {
				//process everything
				System.out.println("If Country and Population is True");
				if(gel.equals("greater")) {
					if(bts == true) {
						query = statement + " where c.isCoastal like 'true' and c.population > " + pop + " and c.co_code like '" + ctyCode + "'";
					}else {
						query = statement + " where c.isCoastal like 'false' and c.population > " + pop + " and c.co_code like '" + ctyCode + "'";
					}
				}
				else if(gel.equals("less")) {
					
					if(bts == true) {
						query = statement + " where c.isCoastal like 'true' and c.population < " + pop + " and c.co_code like '" + ctyCode + "'";
					}else {
						query = statement + " where c.isCoastal like 'false' and c.population < " + pop + " and c.co_code like '" + ctyCode + "'";
					}
					
				}else {
					
					if(bts == true) {
						query = statement + " where c.isCoastal like 'true' and c.population = " + pop + " and c.co_code like '" + ctyCode + "'";
					}else {
						query = statement + " where c.isCoastal like 'false' and c.population = " + pop + " and c.co_code like '" + ctyCode + "'";
					}
				}
				
			}else{
				System.out.println("Checking just Population");
				//check just population and isCoastal
				if(gel.equals("greater")) {
					if(bts == true) {
						query = statement + " where c.isCoastal like 'true' and c.population > " + pop;
					}else {
						query = statement + " where c.isCoastal like 'false' and c.population > " + pop;
					}
					
				}
				else if(gel.equals("less")) {
					
					if(bts == true) {
						query = statement + " where c.isCoastal like 'true' and c.population < " + pop;
					}else {
						query = statement + " where c.isCoastal like 'false' and c.population < " + pop;
					}
					
				}else {
					
					if(bts == true) {
						query = statement + " where c.isCoastal like 'true' and c.population = " + pop;
					}else {
						query = statement + " where c.isCoastal like 'false' and c.population = " + pop;
					}
				}
			}
		}
		else if(!ctyCode.equals("")/*if country is true*/) {
			
			System.out.println("Checking Country not population");
			//check country and check by sea
			query = "select * from city";
			if(bts == true) {
				System.out.println("bts == true");
				
				query = statement + " where c.isCoastal like 'true' and c.co_code like '" + ctyCode + "'";
			}else {
				System.out.println("bts == false");
				
				query = statement + " where c.isCoastal like 'false' and c.co_code like '" + ctyCode + "'";
			}
			
		}else{
			
			System.out.println("Checking By the Sea only");
			//check by the sea only
			if(bts == true) {
				System.out.println("bts == true");
				query = statement + " where c.isCoastal like 'true'";
			}else {
				System.out.println("bts == false");
				query = statement + " where c.isCoastal like 'false'";
			}
		}
		
		ResultSet rs = myStmt.executeQuery(query);
		System.out.println("Fetching Information");

		while(rs.next()) {
			
			SearchClass sc = new SearchClass();
			
			sc.setCtyCode(rs.getString("cty_code"));
			sc.setCtyName(rs.getString("cty_name"));
			sc.setCtry(rs.getString("co_name"));
			sc.setRgn(rs.getString("reg_name"));
			sc.setCityPop(rs.getInt("population"));
			sc.setbTS(rs.getBoolean("isCoastal"));
			sc.setArea(rs.getDouble("areaKM"));
			
			citySearchArraylist.add(sc);
		}
			
		System.out.println("Information fetched and stored!");
		
		return citySearchArraylist;
		
	}

}



