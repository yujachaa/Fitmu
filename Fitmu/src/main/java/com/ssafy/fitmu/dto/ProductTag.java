package com.ssafy.fitmu.dto;

public class ProductTag {
	private int tagId;
	private int imageId;
	private int productId;
	private int top;
	private int left;
	private int storyId;
	
	public ProductTag() {
		
	}
	
	public ProductTag(int tagId, int imageId, int productId, int top, int left, int storyId) {
		this.tagId = tagId;
		this.imageId = imageId;
		this.productId = productId;
		this.top = top;
		this.left = left;
		this.storyId = storyId;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
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

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getStoryId() {
		return storyId;
	}

	public void setStoryId(int storyId) {
		this.storyId = storyId;
	}

	@Override
	public String toString() {
		return "ProductTag [tagId=" + tagId + ", imageId=" + imageId + ", productId=" + productId + ", top=" + top
				+ ", left=" + left + ", storyId=" + storyId + "]";
	}
	
	
	
	
}
