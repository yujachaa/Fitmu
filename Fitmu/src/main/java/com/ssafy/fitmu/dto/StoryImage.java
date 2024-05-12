package com.ssafy.fitmu.dto;

import java.util.Date;

public class StoryImage {
	private int imageId;
	private int storyId;
	private String fileName;
	private String createdAt;
	private String updatedAt;
	
	public StoryImage() {
		
	}
	
	public StoryImage(int imageId, int storyId, String fileName) {
		this.storyId = storyId;
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

	public int getStoryId() {
		return storyId;
	}

	public void setStoryId(int storyId) {
		this.storyId = storyId;
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
		return "StoryImage [imageId=" + imageId + ", storyId=" + storyId + ", fileName=" + fileName + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}
	
	
}
