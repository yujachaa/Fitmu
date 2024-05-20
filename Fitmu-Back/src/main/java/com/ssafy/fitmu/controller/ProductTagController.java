package com.ssafy.fitmu.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitmu.dto.ProductTag;
import com.ssafy.fitmu.service.ProductTagService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/tag-api")
@Tag(name = "태그 컨트롤러")
public class ProductTagController {
	private ProductTagService productTagService;
	
	public ProductTagController(ProductTagService productTagService) {
		this.productTagService = productTagService;
	}

	// 게시글에 달린 태그 전체 조회
	@GetMapping("/story/{stroyId}")
	@Operation(summary = "태그 조회(게시글)")
	public ResponseEntity<?> getAllTagOfStory(@PathVariable("stroyId") int id) {
		List<ProductTag> list = productTagService.seletAllByStroy(id);

		if (list == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<ProductTag>>(list, HttpStatus.OK);
		}
	}
	
	//태그 1개 조회
	@GetMapping("/tag/{tagId}")
	@Operation(summary = "태그 상세 조회")
	public ResponseEntity<?> getTag(@PathVariable("tagId") int id) {
		ProductTag tag = productTagService.selectOne(id);

		if (tag == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<ProductTag>(tag, HttpStatus.OK);
		}
	}	
	
	//태그 등록
	@PostMapping("/regist")
	@Operation(summary = "태그 등록")
	public ResponseEntity<?> registTag(@RequestBody ProductTag tag){
		int result = productTagService.insertTag(tag);
		
		if(result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	//게시글에 달린 태그 전체 삭제 
	@DeleteMapping("/story/{storyId}")
	@Operation(summary = "태그 삭제(게시글)")
	public ResponseEntity<?> deleteAllTagOfStory(@PathVariable("storyId") int id){
		int result = productTagService.deleteAllTagOfStory(id);
		if(result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
}
