package com.cognizant.entity;

public class Product {
	private int productId;
	private String productName;
	private String productDescription;
	private double productPrice;
	private String productCategory;
	private String brand;
	
	private Vendor vendor;
	
	
	
	


	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName="
				+ productName + ", productDescription=" + productDescription
				+ ", productPrice=" + productPrice + ", productCategory="
				+ productCategory +  "]";
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public Product(){}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	
}
