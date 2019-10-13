package com.phamtrong.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="customer")
@Setter
@Getter
public class Customer {
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(Date customerDate) {
		super();
		this.customerDate = customerDate;
	}
	

	public Customer(int id, String name, String address, boolean active, Date customerDate) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.active = active;
		this.customerDate = customerDate;
	}
	public Customer( String name, String address, boolean active, Date customerDate) {
		super();
		 
		this.name = name;
		this.address = address;
		this.active = active;
		this.customerDate = customerDate;
	}
	


	public Customer(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name= "name")
	private String name;
	
	@Column(name="address")
	private String address;

	@Column(name="active")
	private boolean active;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	@Column(name = "customer_date", nullable = false, updatable = false)
	@Temporal(TemporalType.DATE)
	private Date customerDate;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCustomerDate() {
		return customerDate;
	}

	public void setCustomerDate(Date customerDate) {
		this.customerDate = customerDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
	
	

}
