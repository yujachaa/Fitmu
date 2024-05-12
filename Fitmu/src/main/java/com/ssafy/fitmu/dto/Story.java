package com.ssafy.fitmu.dto;

import java.util.Date;

public class Story {
	private int storyId;
	private int userId;
	private String title;
	private String image; //대표 이미지 주소 -> 스토리에 들어가는 전체 이미지들은 story_image 테이블 참조!
	private String content;
	private String productLink;
	private int liked;
	private int category;
	private String createdDate; //timestamp
	private String modifiedDate; //timestamp
	private int viewCnt;
	
	public Story() {
		
	}
	public Story(int storyId, int userId, String title, String image, String content, String productLink, int liked,
			int category) {
		this.userId = userId;
		this.title = title;
		this.image = image;
		this.content = content;
		this.productLink = productLink;
		this.liked = liked;
		this.category = category;
		this.createdDate = new Date().toString();
		this.modifiedDate = new Date().toString();
	}

	public int getStoryId() {
		return storyId;
	}

	public void setStoryId(int storyId) {
		this.storyId = storyId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getProductLink() {
		return productLink;
	}

	public void setProductLink(String productLink) {
		this.productLink = productLink;
	}

	public int getLiked() {
		return liked;
	}

	public void setLiked(int liked) {
		this.liked = liked;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	
	@Override
	public String toString() {
		return "Story [storyId=" + storyId + ", userId=" + userId + ", title=" + title + ", image=" + image
				+ ", content=" + content + ", productLink=" + productLink + ", liked=" + liked + ", category="
				+ category + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", viewCnt=" + viewCnt
				+ "]";
	}
}
