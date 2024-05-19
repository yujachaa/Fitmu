package com.ssafy.fitmu.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitmu.dto.Address;
import com.ssafy.fitmu.service.AddressService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/address-api")
@Tag(name = "주소 컨트롤러")
public class AddressController {
	private AddressService addressService;
	
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}
	@GetMapping("/address/{addressId}")
	@Operation(summary = "주소 상세 조회")
	public ResponseEntity<?> getAddressDetail(@PathVariable("addressId") int addressId){
		Address address = addressService.getAddressDetail(addressId);
		
		if(address == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Address>(address, HttpStatus.OK);
	}
	
	@DeleteMapping("/address/{addressId}")
	@Operation(summary = "주소 삭제")
	public ResponseEntity<?> deleteAddress(@PathVariable("addressId") int addressId){
		int result = addressService.deleteAddress(addressId);
		
		if(result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping("/address/{addressId}")
	@Operation(summary = "주소 수정")
	public ResponseEntity<?> changeAddress(@PathVariable("addressId") int addressId, @RequestBody Address address){
		address.setAddressId(addressId);
		int result = addressService.updateAddress(address);
		
		if(result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PostMapping("/address/{userId}")
	@Operation(summary = "주소 등록")
	public ResponseEntity<?> registAddress(@PathVariable("userId") int userId, @RequestBody Address address){
		List<Address> addressList = addressService.getAddressByUserId(userId);
		if(addressList.size() == 0) {
			address.setIsDefault(1);
		}
		int result = addressService.insertAddress(address);
		
		if(result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/address/user/{userId}")
	@Operation(summary = "유저 주소 조회")
	public ResponseEntity<?> getAddressListByUserId(@PathVariable("userId") int userId){
		List<Address> addressList = addressService.getAddressByUserId(userId);
		
		if(addressList == null || addressList.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Address>>(addressList, HttpStatus.OK);
	}
	
	@GetMapping("/address/default/{userId}")
	@Operation(summary = "기본 배송지 조회")
	public ResponseEntity<?> getDefaultAddress(@PathVariable("userId") int userId){
		List<Address> addressList = addressService.getAddressByUserId(userId);
		Address returnAddress = null;
		for(Address address : addressList) {
			if(address.getIsDefault() == 1) {
				returnAddress = address;
			}
		}
		if(returnAddress == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Address>(returnAddress, HttpStatus.OK);
	}
	
	@PutMapping("/address/{userId}/{addressId}")
	@Operation(summary = "기본 배송지 변경")
	@Transactional
	public ResponseEntity<?> changeDefaultAddress(@PathVariable("userId") int userId, @PathVariable("addressId") int addressId){
		List<Address> addressList = addressService.getAddressByUserId(userId);
		Address defaultAddress = null;
		for(Address address : addressList) {
			if(address.getIsDefault() == 1) {
				defaultAddress = address;
			}
		}
		
		defaultAddress.setIsDefault(0);
		
		int result1 = addressService.updateAddress(defaultAddress);
		if(result1 == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		Address address = addressService.getAddressDetail(addressId);
		
		address.setIsDefault(1);
		
		int result2 = addressService.updateAddress(address);
		if(result2 == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
