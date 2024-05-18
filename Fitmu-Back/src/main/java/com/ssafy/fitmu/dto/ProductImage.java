package com.ssafy.fitmu.dto;

import java.util.Date;

public class ProductImage {
	private int imageId;
	private int productId;
	private int sellerId;
	private String fileName;
	private String createdAt;
	private String updatedAt;
	
	
	public ProductImage(int imageId, int productId, int sellerId, String fileName, String createdAt, String updatedAt) {
		this.imageId = imageId;
		this.productId = productId;
		this.sellerId = sellerId;
		this.fileName = fileName;
		this.createdAt = new Date().toString();
		this.updatedAt = new Date().toString();
	}


	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	@Override
	public String toString() {
		return "ProductImage [imageId=" + imageId + ", productId=" + productId + ", sellerId=" + sellerId
				+ ", fileName=" + fileName + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
	
}
