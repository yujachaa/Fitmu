package com.ssafy.fitmu.dto;

import java.util.Date;

public class Review {
	private int reviewId;
	private int userId;
	private int productId;
	private int sellerId;
	private int rating; //별점
	private String reviewImage; //리뷰이미지 링크
	private String content;
	private int liked; //리뷰 마음에 들어요
	private int blockStatus; //관리자가 리뷰 가렸을 때 체크 0: 일반, 1: 차단됨
	private String createdAt; //timestamp
	
	public Review() {
		
	}
	
	public Review(int reviewId, int userId, int productId, int sellerId, int rating, String reviewImage, String content,
			int liked, int blockStatus ) {
		this.userId = userId;
		this.productId = productId;
		this.sellerId = sellerId;
		this.rating = rating;
		this.reviewImage = reviewImage;
		this.content = content;
		this.liked = liked;
		this.blockStatus = blockStatus;
		this.createdAt = new Date().toString();
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReviewImage() {
		return reviewImage;
	}

	public void setReviewImage(String reviewImage) {
		this.reviewImage = reviewImage;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLiked() {
		return liked;
	}

	public void setLiked(int liked) {
		this.liked = liked;
	}

	public int getBlockStatus() {
		return blockStatus;
	}

	public void setBlockStatus(int blockStatus) {
		this.blockStatus = blockStatus;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Review [reviewId=" + reviewId + ", userId=" + userId + ", productId=" + productId + ", sellerId="
				+ sellerId + ", rating=" + rating + ", reviewImage=" + reviewImage + ", content=" + content + ", liked="
				+ liked + ", blockStatus=" + blockStatus + ", createdAt=" + createdAt + "]";
	}
	
}
