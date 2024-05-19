package com.ssafy.fitmu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitmu.dto.Product;
import com.ssafy.fitmu.dto.Seller;
import com.ssafy.fitmu.service.SellerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/seller-api")
@Tag(name = "판매자 컨트롤러")
public class SellerController {
	
	private final SellerService sellerService;
	
	public SellerController(SellerService sellserService) {
		this.sellerService = sellserService;
	}
	
	@GetMapping("/{userId}")
	@Operation(summary = "userId로 seller 가녀오기")
	public ResponseEntity<?> getSellerByUserId(@PathVariable("userId") int userId){
		Seller seller = sellerService.getSellerByUserId(userId);
		
		if(seller == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<Seller>(seller, HttpStatus.OK);
		}
	}
	
	//판매자 등록
	@PostMapping("/regist")
	@Operation(summary = "판매자 등록")
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
	@Operation(summary = "상품 등록")
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
	@Operation(summary = "문의 답변 등록 및 수정")
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
	@Operation(summary = "문의 답변 삭제")
	public ResponseEntity<?> deleteAnswer(@PathVariable("inquiryId") int inquiryId){
		int result = sellerService.deleteAnswer(inquiryId);
		
		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
}
