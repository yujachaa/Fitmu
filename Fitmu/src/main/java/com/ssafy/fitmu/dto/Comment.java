package com.ssafy.fitmu.dto;

import java.util.Date;

public class Comment {
	private int commentId;
	private int storyId;
	private int userId;
	private String content;
	private String createdDate;
	private String modifiedDate;
	
	public Comment(int commentId, int storyId, int userId, String content) {
		this.commentId = commentId;
		this.storyId = storyId;
		this.userId = userId;
		this.content = content;
		this.createdDate = new Date().toString();
		this.modifiedDate = new Date().toString();
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	
	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", storyId=" + storyId + ", userId=" + userId + ", content="
				+ content + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + "]";
	}
	
	
}
