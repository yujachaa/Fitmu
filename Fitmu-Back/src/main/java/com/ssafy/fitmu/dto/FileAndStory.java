package com.ssafy.fitmu.dto;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class FileAndStory implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int storyId;
	private MultipartFile file;
	private StoryImage storyImage;
	
	public FileAndStory() {}

	public FileAndStory(int storyId, MultipartFile file, StoryImage storyImage) {
		this.storyId = storyId;
		this.file = file;
		this.storyImage = storyImage;
	}
	
	

	public int getStoryId() {
		return storyId;
	}

	public void setStoryId(int storyId) {
		this.storyId = storyId;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public StoryImage getStoryImage() {
		return storyImage;
	}

	public void setStoryImage(StoryImage storyImage) {
		this.storyImage = storyImage;
	}

	@Override
	public String toString() {
		return "FileAndStory [storyId=" + storyId + ", file=" + file + ", storyImage=" + storyImage + "]";
	}

	
}
