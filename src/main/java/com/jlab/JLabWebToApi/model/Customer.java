package com.jlab.JLabWebToApi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {

	private int id;
	private String lastName;
	private String firstName;
	private String gender;
	private String phone;
	private String address;
	private String modifyDt;
	private String modifyBy;
	private String createDt;
	private String createBy;

	public Customer() {
		super();
	}

	public Customer(int id, String lastName, String firstName, String gender, String phone, String address,
			String modifyDt, String modifyBy, String createDt, String createBy) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.gender = gender;
		this.phone = phone;
		this.address = address;
		this.modifyDt = modifyDt;
		this.modifyBy = modifyBy;
		this.createDt = createDt;
		this.createBy = createBy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getModifyDt() {
		return modifyDt;
	}

	public void setModifyDt(String modifyDt) {
		this.modifyDt = modifyDt;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public String getCreateDt() {
		return createDt;
	}

	public void setCreateDt(String createDt) {
		this.createDt = createDt;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	// Getters and Setters

}
