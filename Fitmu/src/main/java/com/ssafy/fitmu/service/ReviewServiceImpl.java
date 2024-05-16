package com.ssafy.fitmu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.fitmu.dao.ReviewDao;
import com.ssafy.fitmu.dto.Review;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	private ReviewDao reviewDao;
	
	public ReviewServiceImpl(ReviewDao reviewDao) {
		this.reviewDao = reviewDao;
	}
	
	@Override
	public Review reviewDetail(int id) {
		return reviewDao.selectOne(id);
	}

	@Override
	public List<Review> seletAll(int productId) {
		return reviewDao.selectAll(productId);
	}

	@Override
	public int registReview(Review review) {
		return reviewDao.insertReview(review);
	}

	@Override
	public int updateReview(Review review) {
		return reviewDao.updateReview(review);
	}

	@Override
	public int deleteReview(int id) {
		return reviewDao.deleteReview(id);
	}

	@Override
	public int addLike(int id) {
		return reviewDao.addLike(id);
	}

	@Override
	public int cancelLike(int id) {
		return reviewDao.cancelLike(id);
	}

	@Override
	public List<Review> selectAllOrderByLike(int productId) {
		return reviewDao.selectAllOrderByLike(productId);
	}

	@Override
	public int addProductRating(int productId, int rating) {
		Map<String, Integer> map = new HashMap<>();
		map.put("productId", productId);
		map.put("rating", rating);
		return reviewDao.addProductRating(map);
	}

	@Override
	public int cancelProductRating(int productId, int rating) {
		Map<String, Integer> map = new HashMap<>();
		map.put("productId", productId);
		map.put("rating", rating);
		return reviewDao.cancelProductRating(map);
	}

}
