package com.sportyshoes.model;



import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id",updatable = false,nullable = false)
	private int user_id;
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private String username;
	@Column
	private String user_status;
	@Column
	private Date user_createdDate;
	@Column
	private String securityQuestion;
	@Column
	private String securityAnswer;
	@Column
	private int phone;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int user_id, String email, String password, String username, String user_status, Date user_createdDate,
			String securityQuestion, String securityAnswer, int phone) {
		super();
		this.user_id = user_id;
		this.email = email;
		this.password = password;
		this.username = username;
		this.user_status = user_status;
		this.user_createdDate = user_createdDate;
		this.securityQuestion = securityQuestion;
		this.securityAnswer = securityAnswer;
		this.phone = phone;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUser_status() {
		return user_status;
	}
	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}
	public Date getUser_createdDate() {
		return user_createdDate;
	}
	public void setUser_createdDate(Date user_createdDate) {
		this.user_createdDate = user_createdDate;
	}
	public String getSecurityQuestion() {
		return securityQuestion;
	}
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}
	public String getSecurityAnswer() {
		return securityAnswer;
	}
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", email=" + email + ", password=" + password + ", username=" + username
				+ ", user_status=" + user_status + ", user_createdDate=" + user_createdDate + ", securityQuestion="
				+ securityQuestion + ", securityAnswer=" + securityAnswer + ", phone=" + phone + "]";
	}
	
	
	
}
