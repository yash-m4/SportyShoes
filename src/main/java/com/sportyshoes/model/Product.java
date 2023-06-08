package com.sportyshoes.model;
import java.sql.Date;
import java.util.Arrays;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="product_id",updatable = false,nullable = false)
	private int product_id;
	@Column
	private String product_name;
	@Column
	private Date product_addedDate;
	@Column
	private String product_description;
	@Column
	private int product_price;

	@Column
	private String product_addedBy;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	private String name;
    private String type;
	 
    private String imageUrl;
    
    
    
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@ManyToOne
	@JoinColumn(name = "category_id")
	 private Category category;
	
	 @Lob
	    @Column(name = "image_data")
	    private byte[] imageData;


	public int getProduct_id() {
		return product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public Date getProduct_addedDate() {
		return product_addedDate;
	}

	public void setProduct_addedDate(Date product_addedDate) {
		this.product_addedDate = product_addedDate;
	}

	public String getProduct_description() {
		return product_description;
	}

	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}

	public int getProduct_price() {
		return product_price;
	}

	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}

	

	public String getProduct_addedBy() {
		return product_addedBy;
	}

	public void setProduct_addedBy(String product_addedBy) {
		this.product_addedBy = product_addedBy;
	}



	

	

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	

	public Product(int product_id, String product_name, Date product_addedDate, String product_description,
			int product_price, String product_addedBy, Category category, byte[] imageData) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.product_addedDate = product_addedDate;
		this.product_description = product_description;
		this.product_price = product_price;
		this.product_addedBy = product_addedBy;
		this.category = category;
		this.imageData = imageData;
	}

	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", product_name=" + product_name + ", product_addedDate="
				+ product_addedDate + ", product_description=" + product_description + ", product_price="
				+ product_price + ", product_addedBy=" + product_addedBy + ", category=" + category + ", imageData="
				+ Arrays.toString(imageData) + "]";
	}

	



	

	
	
	
}
