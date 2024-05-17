package com.ssafy.fitmu.dto;

public class Seller {
	private int sellerId;
	private int userId;
	private String companyName;
	private String representative;
	private String address;
	private String customerCenter;
	private String email;
	private int registrationNum;
	
	public Seller () {
		
	}
	
	public Seller(int sellerId, int userId, String companyName, String representative, String address,
			String customerCenter, String email, int registrationNum) {
		this.sellerId = sellerId;
		this.userId = userId;
		this.companyName = companyName;
		this.representative = representative;
		this.address = address;
		this.customerCenter = customerCenter;
		this.email = email;
		this.registrationNum = registrationNum;
	}
	
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getRepresentative() {
		return representative;
	}
	public void setRepresentative(String representative) {
		this.representative = representative;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCustomerCenter() {
		return customerCenter;
	}
	public void setCustomerCenter(String customerCenter) {
		this.customerCenter = customerCenter;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRegistrationNum() {
		return registrationNum;
	}
	public void setRegistrationNum(int registrationNum) {
		this.registrationNum = registrationNum;
	}
	
	@Override
	public String toString() {
		return "Seller [sellerId=" + sellerId + ", userId=" + userId + ", companyName=" + companyName
				+ ", representative=" + representative + ", address=" + address + ", customerCenter=" + customerCenter
				+ ", email=" + email + ", registrationNum=" + registrationNum + "]";
	}
	
	
}