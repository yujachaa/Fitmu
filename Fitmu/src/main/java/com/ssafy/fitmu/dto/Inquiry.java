package com.ssafy.fitmu.dto;

import java.util.Date;

public class Inquiry {
	private int inquiryId;
	private int userId;
	private int productId;
	private int sellerId;
	private int isBuy;
	private int isPrivate;
	private int isAnswered;
	private String createdAt;
	private String category;
	private String context;
	private String answer;

	public Inquiry(int inquiryId, int userId, int productId, int sellerId, int isBuy, int isPrivate, int isAnswered,
			String category, String context, String answer) {
		this.inquiryId = inquiryId;
		this.userId = userId;
		this.productId = productId;
		this.sellerId = sellerId;
		this.isBuy = isBuy;
		this.isPrivate = isPrivate;
		this.isAnswered = isAnswered;
		this.createdAt = new Date().toString();
		this.category = category;
		this.context = context;
		this.answer = answer;
	}

	public int getInquiryId() {
		return inquiryId;
	}

	public void setInquiryId(int inquiryId) {
		this.inquiryId = inquiryId;
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

	public int getIsBuy() {
		return isBuy;
	}

	public void setIsBuy(int isBuy) {
		this.isBuy = isBuy;
	}

	public int getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(int isPrivate) {
		this.isPrivate = isPrivate;
	}

	public int getIsAnswered() {
		return isAnswered;
	}

	public void setIsAnswered(int isAnswered) {
		this.isAnswered = isAnswered;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "Inquiry [inquiryId=" + inquiryId + ", userId=" + userId + ", productId=" + productId + ", sellerId="
				+ sellerId + ", isBuy=" + isBuy + ", isPrivate=" + isPrivate + ", isAnswered=" + isAnswered
				+ ", createdAt=" + createdAt + ", category=" + category + ", context=" + context + ", answer=" + answer
				+ "]";
	}

}
