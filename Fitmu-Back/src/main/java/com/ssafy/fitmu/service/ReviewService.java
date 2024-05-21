package com.ssafy.fitmu.service;

import java.util.List;

import com.ssafy.fitmu.dto.Review;

public interface ReviewService {
	//리뷰상세조회
	public Review reviewDetail(int id);
	//리뷰전체조회 -> 최신순정렬
	public List<Review> seletAll(int productId);
	//리뷰 전체 조회 -> 좋아요 순 조회
	public List<Review> selectAllOrderByLike(int productId);
	//리뷰 등록
	public int registReview(Review review);
	//리뷰 수정
	public int updateReview(Review review);
	//리뷰 삭제
	public int deleteReview(int id);
	//리뷰 좋아요 추가
	public int addLike(int id);
	//리뷰 좋아요 취소
	public int cancelLike(int id);
	//상품 별점(rating) 추가
	public int addProductRating(int productId, int rating);
	//상품 별점(rating) 삭제
	public int cancelProductRating(int productId, int rating);
	
	public List<Review> selectByUser(int userId);
}
