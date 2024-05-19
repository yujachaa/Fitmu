package com.ssafy.fitmu.controller;

import java.util.List;

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

import com.ssafy.fitmu.dto.Inquiry;
import com.ssafy.fitmu.dto.Review;
import com.ssafy.fitmu.service.InquiryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/inquiry-api")
@Tag(name = "문의 컨트롤러")
public class InquiryController {
	private InquiryService inquiryService;

	public InquiryController(InquiryService inquiryService) {
		this.inquiryService = inquiryService;
	}

	// 문의 상세 조회
	@GetMapping("/{inquiryId}")
	@Operation(summary = "문의 상세 조회")
	public ResponseEntity<?> inquiryDetail(@PathVariable("inquiryId") int id) {
		Inquiry inquiry = inquiryService.inquiryDetail(id);

		if (inquiry == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Inquiry>(inquiry, HttpStatus.OK);
		}
	}

	// 문의 전체 조회 -> 사용자 기준
	@GetMapping("/user/{userId}")
	@Operation(summary = "문의 조회 (사용자)")
	public ResponseEntity<?> getInquiryByUser(@PathVariable("userId") int id){
		List<Inquiry> list = inquiryService.selectAllByUser(id);
		
		if (list == null || list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<Inquiry>>(list, HttpStatus.OK);
		}
	}
	
	//문의 전체 조회 -> 상품 기준
	@GetMapping("/product/{productId}")
	@Operation(summary = "문의 조회 (상품)")
	public ResponseEntity<?> getInquiryByProduct(@PathVariable("productId") int id){
		List<Inquiry> list = inquiryService.selectAllByProduct(id);
		
		if (list == null || list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<Inquiry>>(list, HttpStatus.OK);
		}
	}
	
	// 문의 등록
	@PostMapping("/regist")
	@Operation(summary = "문의 등록")
	public ResponseEntity<?> inquiryRegist(@RequestBody Inquiry inquiry) {
		int result = inquiryService.registInquiry(inquiry);

		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			//리뷰 등록 완료했으면 상품에 별점 업데이트
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	// 리뷰 삭제
	@DeleteMapping("/{inquiryId}")
	@Operation(summary = "문의 삭제")
	public ResponseEntity<?> inquiryDelete(@PathVariable("inquiryId") int id) {
		
		int result = inquiryService.deleteInquiry(id);

		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
}
