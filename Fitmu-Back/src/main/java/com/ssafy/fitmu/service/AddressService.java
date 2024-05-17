package com.ssafy.fitmu.service;

import java.util.List;

import com.ssafy.fitmu.dto.Address;

public interface AddressService {
	public List<Address> getAddressByUserId(int userId);
	public int deleteAddress(int addressId);
	public int insertAddress(Address address);
	public int updateAddress(Address address);
	public Address getAddressDetail(int addressId);
}