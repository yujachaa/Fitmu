package com.ssafy.fitmu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitmu.dto.Product;
import com.ssafy.fitmu.dto.Seller;
import com.ssafy.fitmu.service.SellerService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin("*")
@RequestMapping("/seller-api")
@Tag(name = "판매자 컨트롤러")
public class SellerController {
	
	private final SellerService sellerService;
	
	public SellerController(SellerService sellserService) {
		this.sellerService = sellserService;
	}
	
	//판매자 등록
	@PostMapping("/regist")
	public ResponseEntity<?> registSeller(@RequestBody Seller seller){
		int result = sellerService.insertSeller(seller);
		
		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	//상품 등록
	@PostMapping("/product-regist")
	public ResponseEntity<?> registProduct(@RequestBody Product product){
		int result = sellerService.insertProduct(product);
		
		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}	
	
	//문의 답변 등록 + 수정
	@PutMapping("/answer/{inquiryId}")
	public ResponseEntity<?> registAnswer(@RequestBody String answer, @PathVariable("inquiryId") int inquiryId){
		int result = sellerService.insertAnswer(inquiryId, answer);
		
		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	//문의 답변 삭제
	@DeleteMapping("/answer/{inquiryId}")
	public ResponseEntity<?> deleteAnswer(@PathVariable("inquiryId") int inquiryId){
		int result = sellerService.deleteAnswer(inquiryId);
		
		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
}
