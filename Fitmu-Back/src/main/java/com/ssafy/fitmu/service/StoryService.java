package com.ssafy.fitmu.service;

import java.util.List;

import com.ssafy.fitmu.dto.ProductImage;
import com.ssafy.fitmu.dto.SearchCondition;
import com.ssafy.fitmu.dto.Story;
import com.ssafy.fitmu.dto.StoryImage;

public interface StoryService {
	public int insertStory(Story story);
	public int updateStory(Story story);
	public int deleteStory(int storyId);
	public List<Story> selectAll();
	public List<Story> searchStory(SearchCondition condition); // 카테고리, 제목, 작성자, 내용, 조회수, 좋아요 >> 오름차순, 내림차순
	public List<Story> selectByUserId(int userId);
	public List<Story> popularStory();
	public List<Story> likeStory();
	public List<Story> latestStory();
	public Story selectOne(int storyId);
	public int increaseLikeCnt(int storyId);
	public int decreaseLikeCnt(int storyId);
	public int insertScrapBook(int userId, int storyId);
	public int deleteScrapBook(int userId, int storyId);
	public int insertStoryImage(StoryImage storyImage);
	public List<StoryImage> getImageByStoryId(int storyId);
}
