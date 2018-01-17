package com.employeeDB;

import java.util.ArrayList;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

@ManagedBean
public class ProductController {
	
	ArrayList<Product> products;
	private DAO dao;


	public ProductController() {
		super();
		products = new ArrayList<Product>();
		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ProductController(ArrayList<Product> products) {
		super();
		this.products = products;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	
	
	public void loadProducts() throws Exception {
		products.clear();
		if (dao != null) {
			try {
				products = dao.loadProducts();
				System.out.println(products.size());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public String addProduct(Product product) {
		if (dao != null) {
			try {
				dao.addProduct(product);
				return "index";
			} catch (MySQLIntegrityConstraintViolationException e) {
				FacesMessage message = new FacesMessage("Error: Product ID " + product.getProductID() + " already exists");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			} catch (CommunicationsException e) {
				FacesMessage message = new FacesMessage("Error: Cannot connect to Database");
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			} catch (Exception e) {
				FacesMessage message = new FacesMessage("Error while trying to insert Product " + product.getProductID());
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;
			}
		}
		return null;
	}


}
