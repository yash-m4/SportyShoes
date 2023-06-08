package com.sportyshoes.model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="category_id",updatable = false,nullable = false)
	private int category_id;
	@Column
	private String category_name;
	@Column
	private Date category_createdDate;
	@Column
	private String category_createdBy;
	
	@OneToMany(mappedBy = "category")
    private List<Product> products;
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
	public Category(int category_id, String category_name, Date category_createdDate, String category_createdBy,
			List<Product> products) {
		super();
		this.category_id = category_id;
		this.category_name = category_name;
		this.category_createdDate = category_createdDate;
		this.category_createdBy = category_createdBy;
		this.products = products;
	}




	

	public List<Product> getProducts() {
		return products;
	}




	public void setProducts(List<Product> products) {
		this.products = products;
	}




	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public Date getCategory_createdDate() {
		return category_createdDate;
	}
	public void setCategory_createdDate(Date category_createdDate) {
		this.category_createdDate = category_createdDate;
	}
	public String getCategory_createdBy() {
		return category_createdBy;
	}
	public void setCategory_createdBy(String category_createdBy) {
		this.category_createdBy = category_createdBy;
	}




	@Override
	public String toString() {
		return "Category [category_id=" + category_id + ", category_name=" + category_name + ", category_createdDate="
				+ category_createdDate + ", category_createdBy=" + category_createdBy + ", products=" + products + "]";
	}

	
	
	
}
