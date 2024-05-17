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

import com.ssafy.fitmu.dto.Review;
import com.ssafy.fitmu.service.ReviewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin("*")
@RequestMapping("/review-api")
@Tag(name = "리뷰 컨트롤러")
public class ReviewController {
	private ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	// 리뷰 상세 조회
	@GetMapping("/{reviewId}")
	@Operation(summary = "리뷰 상세 조회")
	public ResponseEntity<?> reviewDetail(@PathVariable("reviewId") int id) {
		Review review = reviewService.reviewDetail(id);

		if (review == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Review>(review, HttpStatus.OK);
		}
	}

	// 리뷰 등록
	@PostMapping("/regist")
	@Operation(summary = "리뷰 등록")
	public ResponseEntity<?> reviewRegist(@RequestBody Review review) {
		int result = reviewService.registReview(review);

		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			//리뷰 등록 완료했으면 상품에 별점 업데이트
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	// 리뷰 수정
	@PutMapping("/{reviewId}")
	@Operation(summary = "리뷰 수정")
	@Transactional
	public ResponseEntity<?> reviewUpdate(@PathVariable("reviewId") int id, @RequestBody Review review) {
		//수정 전 리뷰의 rating 저장
		Review before = reviewService.reviewDetail(id);
		int beforeRating = before.getRating();
		
		if(review.getRating() != beforeRating) { //바뀐 별점이랑 다르면
			//이전 별점 삭제
			reviewService.cancelProductRating(review.getProductId(), beforeRating);
			//새 별점 추가
			reviewService.addProductRating(review.getProductId(), review.getRating());
		}
		
		int result = reviewService.updateReview(review);

		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	// 리뷰 삭제
	@DeleteMapping("/{reviewId}")
	@Operation(summary = "리뷰 삭제")
	@Transactional
	public ResponseEntity<?> reviewDelete(@PathVariable("reviewId") int id) {
		//삭제 전 리뷰의 rating 삭제
		Review review = reviewService.reviewDetail(id);
		reviewService.cancelProductRating(review.getProductId(), review.getRating());
		
		int result = reviewService.deleteReview(id);

		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	// 리뷰 전체 조회 -> 최신순
	@GetMapping("/review/{productId}")
	@Operation(summary = "리뷰 상품기준 전체 조회(최신순)")
	public ResponseEntity<?> reviewAll(@PathVariable("productId") int id) {
		List<Review> list = reviewService.seletAll(id);

		if (list == null || list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<Review>>(list, HttpStatus.OK);
		}
	}
	
	// 리뷰 전체 조회 -> 좋아요 순
	@GetMapping("/review/{productId}/like")
	@Operation(summary = "리뷰 상품기준 전체 조회(좋아요순)")
	public ResponseEntity<?> reviewAllOrderByLike(@PathVariable("productId") int id) {
		List<Review> list = reviewService.selectAllOrderByLike(id);
		
		if (list == null || list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<Review>>(list, HttpStatus.OK);
		}
	}

	// 리뷰 좋아요 증가
	@PutMapping("/like/{reviewId}")
	@Operation(summary = "리뷰 좋아요 증가")
	public ResponseEntity<?> likeReview(@PathVariable("reviewId") int id) {
		int result = reviewService.addLike(id);

		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	// 리뷰 좋아요 감소
	@PutMapping("/dislike/{reviewId}")
	@Operation(summary = "리뷰 좋아요 감소")
	public ResponseEntity<?> dislikeReview(@PathVariable("reviewId") int id) {
		int result = reviewService.cancelLike(id);

		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

}
