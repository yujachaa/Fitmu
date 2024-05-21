package com.ssafy.fitmu.dao;

import java.util.List;
import java.util.Map;

import com.ssafy.fitmu.dto.Review;

public interface ReviewDao {
	//리뷰 상세 조회
	public Review selectOne(int reviewId);
	//리뷰 전체 조회 -> 최신순정렬
	public List<Review> selectAll(int productId);
	//리뷰 전체 조회 -> 좋아요 순 조회
	public List<Review> selectAllOrderByLike(int productId);
	//리뷰 등록
	public int insertReview(Review review);
	//리뷰 수정
	public int updateReview(Review review);
	//리뷰 삭제
	public int deleteReview(int reviewId);
	//리뷰 좋아요 like
	public int addLike(int reviewId);
	//리뷰 좋아요 취소
	public int cancelLike(int reviewId);
	//상품 별점(rating) 추가
	public int addProductRating(Map<String, Integer> map);
	//상품 별점(rating) 삭제
	public int cancelProductRating(Map<String, Integer> map);
	
	public List<Review> selectByUser(int userId);
	
}
