package com.phamtrong.app.dto;

import java.util.Date;

import com.phamtrong.app.entity.Customer;

public class CustomerDto extends Customer{

	public CustomerDto(Date customerDate) {
		super(customerDate);
		// TODO Auto-generated constructor stub
	}

	public CustomerDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Date fromCustomerDate;

	private Date toCustomerDate;

	public Date getFromCustomerDate() {
		return fromCustomerDate;
	}

	public void setFromCustomerDate(Date fromCustomerDate) {
		this.fromCustomerDate = fromCustomerDate;
	}

	public Date getToCustomerDate() {
		return toCustomerDate;
	}

	public void setToCustomerDate(Date toCustomerDate) {
		this.toCustomerDate = toCustomerDate;
	}

	
}
