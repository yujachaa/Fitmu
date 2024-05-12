package com.ssafy.fitmu.dto;

import java.util.Date;

public class Product {
	private int productId;
	private int sellerId;
	private int categoryId;
	private String brand;
	private String name;
	private int price;
	private int discountRate;
	private int deliveryFee;
	private int specialPrice;
	private String createdAt;
	private String updatedAt;
	private int ratingSum;
	private int ratingCnt;
	private String productDetail;
	
	public Product(int productId, int sellerId, int categoryId, String brand, String name, int price, int discountRate,
			int deliveryFee, int specialPrice, int ratingSum, int ratingCnt,
			String productDetail) {
		this.productId = productId;
		this.sellerId = sellerId;
		this.categoryId = categoryId;
		this.brand = brand;
		this.name = name;
		this.price = price;
		this.discountRate = discountRate;
		this.deliveryFee = deliveryFee;
		this.specialPrice = specialPrice;
		this.createdAt = new Date().toString();
		this.updatedAt = new Date().toString();
		this.ratingSum = ratingSum;
		this.ratingCnt = ratingCnt;
		this.productDetail = productDetail;
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
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(int discountRate) {
		this.discountRate = discountRate;
	}
	public int getDeliveryFee() {
		return deliveryFee;
	}
	public void setDeliveryFee(int deliveryFee) {
		this.deliveryFee = deliveryFee;
	}
	public int getSpecialPrice() {
		return specialPrice;
	}
	public void setSpecialPrice(int specialPrice) {
		this.specialPrice = specialPrice;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	public int getRatingSum() {
		return ratingSum;
	}
	public void setRatingSum(int ratingSum) {
		this.ratingSum = ratingSum;
	}
	public int getRatingCnt() {
		return ratingCnt;
	}
	public void setRatingCnt(int ratingCnt) {
		this.ratingCnt = ratingCnt;
	}
	public String getProductDetail() {
		return productDetail;
	}
	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", sellerId=" + sellerId + ", categoryId=" + categoryId + ", brand="
				+ brand + ", name=" + name + ", price=" + price + ", discountRate=" + discountRate + ", deliveryFee="
				+ deliveryFee + ", specialPrice=" + specialPrice + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", ratingSum=" + ratingSum + ", ratingCnt=" + ratingCnt + ", productDetail="
				+ productDetail + "]";
	}
	
	
}
