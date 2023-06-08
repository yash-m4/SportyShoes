package com.sportyshoes.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Kart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="kart_id",updatable = false,nullable = false)
	private int kart_id;
	
	@ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private Date purchaseDate;

    // Constructors, getters, and setters

    public Kart() {
    }

    public Kart(Product product, User user, Date purchaseDate) {
        this.product = product;
        this.user = user;
        this.purchaseDate = purchaseDate;
    }
    
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    
	public int getKart_id() {
		return kart_id;
	}

	public void setKart_id(int kart_id) {
		this.kart_id = kart_id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Kart [kart_id=" + kart_id + ", product=" + product + ", user=" + user + "]";
	}
    
    
	
	
	
}
