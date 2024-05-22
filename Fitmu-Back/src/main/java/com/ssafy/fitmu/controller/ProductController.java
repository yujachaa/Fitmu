package com.ssafy.fitmu.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.fitmu.dto.Product;
import com.ssafy.fitmu.dto.ProductImage;
import com.ssafy.fitmu.dto.SearchCondition;
import com.ssafy.fitmu.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/product-api")
@Tag(name = "상품 컨트롤러")
public class ProductController {
	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	//상품 1개 상세조회
	@GetMapping("/{productId}")
	@Operation(summary = "상품 상세 조회")
	public ResponseEntity<?> productDetail(@PathVariable("productId") int id){
		Product product = productService.selectOne(id);
		
		if(product == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		}
	}
	
	//전체조회
	@GetMapping("/product")
	@Operation(summary = "상품 전체 조회")
	public ResponseEntity<?> productAll(){
		List<Product> productList = productService.selectAll();
		
		if(productList == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
		}
	}
	
	//카테고리별 조회
	@GetMapping("/category/{categoryId}")
	@Operation(summary = "상품 조회(카테고리)")
	public ResponseEntity<?> categoryProduct(@PathVariable("categoryId") String categoryId){
		SearchCondition condition = new SearchCondition();
		condition.setKey("category_id");
		condition.setWord(categoryId);
		List<Product> list = productService.searchByCondition(condition);
		
		if(list == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		}
	}
	
	//판매자별 조회
	@GetMapping("/seller/{sellerId}")
	@Operation(summary = "상품 조회(판매자)")
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
	@Operation(summary = "상품 조회(할인율 기준 이상)")
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
	@Operation(summary = "상품 스크랩 등록")
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
	@Operation(summary = "상품 스크랩 삭제")
	public ResponseEntity<?> removeProductScrap(@PathVariable("productId") int productId, @PathVariable("userId") int userId){
		int result = productService.deleteProductScrap(userId, productId);
		
		if(result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	@PostMapping("/upload")
	@Operation(summary = "상품 이미지 업로드")
	public ResponseEntity<?> uploadProdcutImage(@ModelAttribute("files") MultipartFile[] files) {
		List<String> filenames = new ArrayList<>();
		for(MultipartFile file : files ) {
			UUID uuid = UUID.randomUUID();
			String originalFilename = file.getOriginalFilename(); // 원본 파일 이름
			String extension = originalFilename.substring(originalFilename.lastIndexOf(".")); // 파일 확장자 추출
			String newFilename = uuid.toString() + extension; // UUID를 파일 이름에 추가
			
			try {
				filenames.add(newFilename);
				file.transferTo(new File("C:/Fitmu/Fitmu/Fitmu-Front/fitmu/src/assets/image/product", newFilename));
			}catch(Exception e) {
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<List<String>>(filenames, HttpStatus.OK);
	}
	
	@PostMapping("/imageUpload")
	@Operation(summary = "상품 이미지 DB에 저장")
	@Transactional
	public ResponseEntity<?> insertProductImage(@RequestBody ProductImage productImage) {
			int result = productService.insertProductImage(productImage);
			
			if(result == 0) {
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}else {
				return new ResponseEntity<Void>(HttpStatus.OK);
			}
	}
	
	@GetMapping("/image/{productId}")
	@Operation(summary = "상품 이미지 조회(상품 id 기준)")
	public ResponseEntity<?> getProductImageByProductId(@PathVariable("productId") int productId){
		List<ProductImage> productImageList = productService.getImageByProductId(productId);
		
		if(productImageList == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<ProductImage>>(productImageList, HttpStatus.OK);
		}
	}
	
	@GetMapping("image")
	public ResponseEntity<?> getProductImage(){
		List<ProductImage> productImageList = productService.getProductImage();
		
		if(productImageList == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<ProductImage>>(productImageList, HttpStatus.OK);
	}
	
	@GetMapping("/search")
	@Operation(summary = "상품 검색")
	public ResponseEntity<?> productSearch(@ModelAttribute SearchCondition condition){
		System.out.println(condition);
		List<Product> productList = productService.searchByCondition(condition);
		
		if(productList == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
		}
	}
	
	@PostMapping("/regist")
	@Operation(summary = "상품 등록")
	public ResponseEntity<?> registProduct(@RequestBody Product product){
		int result = productService.insertProduct(product);
		
		List<Product> productList = productService.selectAll();
		
		if(result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}else {
			int ID = productList.get(productList.size()-1).getProductId();
			return new ResponseEntity<Integer>(ID, HttpStatus.OK);
		}
	}
}
