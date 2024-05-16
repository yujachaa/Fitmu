package com.ssafy.fitmu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.fitmu.dao.AddressDao;
import com.ssafy.fitmu.dto.Address;

@Service
public class AddressServiceImpl implements AddressService{
	private AddressDao addressDao;
	public AddressServiceImpl(AddressDao addressDao) {
		this.addressDao = addressDao;
	}
	
	@Override
	public List<Address> getAddressByUserId(int userId) {
		return addressDao.getAddressByUserId(userId);
	}

	@Override
	public int deleteAddress(int addressId) {
		return addressDao.deleteAddress(addressId);
	}

	@Override
	public int insertAddress(Address address) {
		return addressDao.insertAddress(address);
	}

	@Override
	public int updateAddress(Address address) {
		return addressDao.updateAddress(address);
	}

	@Override
	public Address getAddressDetail(int addressId) {
		return addressDao.getAddressDetail(addressId);
	}

}
