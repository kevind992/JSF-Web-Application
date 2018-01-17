package com.employeeDB;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Product {
	
	private int productID;
	private String description;
	
	public Product() {
	}	
	
	public Product(int productID, String description) {
		super();
		this.productID = productID;
		this.description = description;
	}
	
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	
	
}
