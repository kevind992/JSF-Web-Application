package com.employeeDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DAO {
	private DataSource mysqlDS;
	
	/* ======================================================================================================
	 * Constructor
	 * ====================================================================================================== */
	public DAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/jdbc/employeesdb14";
		mysqlDS = (DataSource) context.lookup(jndiName);
	}
	
	
	public ArrayList<Product> loadProducts() throws Exception {
		ArrayList<Product> products = new ArrayList<Product>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();

		String sql = "select * from product";

		myStmt = myConn.createStatement();

		myRs = myStmt.executeQuery(sql);

		// process result set
		while (myRs.next()) {
				
			// retrieve data from result set row
			int prodid = myRs.getInt("prodid");
			String desc = myRs.getString("descrip");

			// create new student object
			Product product = new Product(prodid, desc);

			products.add(product);
		}	
		return products;
	}

	public void addProduct(Product product) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();
		String sql = "insert into product values (?, ?)";
		myStmt = myConn.prepareStatement(sql);
		myStmt.setInt(1, product.getProductID());
		myStmt.setString(2, product.getDescription());
		myStmt.execute();			
	}

	public Product loadProduct(int ID) throws Exception {
		Product product = new Product();
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		myConn = mysqlDS.getConnection();

		String sql = "select * from product where prodid = " + ID;

		myStmt = myConn.createStatement();

		myRs = myStmt.executeQuery(sql);

		// process result set
		while (myRs.next()) {
				
			// retrieve data from result set row
			int prodid = myRs.getInt("prodid");
			String desc = myRs.getString("descrip");

			// create new product object
			product = new Product(prodid, desc);

		}	
		return product;
	}
}
