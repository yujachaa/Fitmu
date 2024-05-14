package com.ssafy.fitmu.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitmu.dto.Product;
import com.ssafy.fitmu.dto.SearchCondition;
import com.ssafy.fitmu.service.ProductService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin("*")
@RequestMapping("/product-api")
@Tag(name = "상품 컨트롤러")
public class ProductController {
	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	//상품 1개 상세조회
	@GetMapping("/{productId}")
	public ResponseEntity<?> productDetail(@PathVariable("productId") int id){
		Product product = productService.selectOne(id);
		
		if(product == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//전체조회
	@GetMapping("/product")
	public ResponseEntity<?> productAll(){
		List<Product> productList = productService.selectAll();
		
		if(productList == null || productList.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
		}
	}
	
	//카테고리별 조회
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<?> categoryProduct(@PathVariable("categoryId") String categoryId){
		SearchCondition condition = new SearchCondition();
		condition.setKey("category_id");
		condition.setWord(categoryId);
		List<Product> list = productService.searchByCondition(condition);
		
		if(list == null || list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		}
	}
	
	//판매자별 조회
	@GetMapping("/seller/{sellerId}")
	public ResponseEntity<?> sellerProduct(@PathVariable("sellerId") String sellerId){
		SearchCondition condition = new SearchCondition();
		condition.setKey("seller_id");
		condition.setWord(sellerId);
		List<Product> list = productService.searchByCondition(condition);
		
		if(list == null || list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		}
	}
	
	//할인율 조회
	@GetMapping("/discount/{discount}")
	public ResponseEntity<?> discountProduct(@PathVariable("discount") int discount){
		List<Product> list = productService.getDiscountList(discount);
		
		if(list == null || list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		}
	}
	
	//제품 스크랩 등록
	@PostMapping("/scrap/{productId}/user/{userId}")
	public ResponseEntity<?> addProductScrap(@PathVariable("productId") int productId, @PathVariable("userId") int userId){
		int result = productService.insertProductScrap(userId, productId);
		
		if(result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	//제품 스크랩 삭제
	@DeleteMapping("/scrap/{productId}/user/{userId}")
	public ResponseEntity<?> removeProductScrap(@PathVariable("productId") int productId, @PathVariable("userId") int userId){
		int result = productService.deleteProductScrap(userId, productId);
		
		if(result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	
}
