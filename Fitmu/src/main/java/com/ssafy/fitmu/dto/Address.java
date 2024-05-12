package com.ssafy.fitmu.dto;

public class Address {
	private int addressId;
	private int userId;
	private String addressName;
	private String receiver;
	private String phoneNumber;
	private String address1;
	private String address2;
	private int isDefault;
	
	public Address(int addressId, int userId, String addressName, String receiver, String phoneNumber, String address1,
			String address2, int isDefault) {
		this.addressId = addressId;
		this.userId = userId;
		this.addressName = addressName;
		this.receiver = receiver;
		this.phoneNumber = phoneNumber;
		this.address1 = address1;
		this.address2 = address2;
		this.isDefault = isDefault;
	}
	
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public int getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}
	
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", userId=" + userId + ", addressName=" + addressName + ", receiver="
				+ receiver + ", phoneNumber=" + phoneNumber + ", address1=" + address1 + ", address2=" + address2
				+ ", isDefault=" + isDefault + "]";
	}
	
	
}
