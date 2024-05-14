package com.ssafy.fitmu.dao;

import java.util.List;
import java.util.Map;

import com.ssafy.fitmu.dto.SearchCondition;
import com.ssafy.fitmu.dto.Story;

public interface StoryDao {
	public int insertStory(Story story);
	public int updateStory(Story story);
	public int deleteStory(int storyId);
	public List<Story> selectAll();
	public List<Story> searchStory(SearchCondition condition); // 카테고리, 제목, 작성자, 내용, 조회수, 좋아요 >> 오름차순, 내림차순
	public List<Story> selectByUserId(int userId);
	public List<Story> popularStroy();
	public List<Story> latestStroy();
	public Story selectOne(int storyId);
	public int updateViewCnt(int storyId);
	public int increaseLikeCnt(int storyId);
	public int decreaseLikeCnt(int storyId);
	public int insertScrapBook(Map<String,Integer> map);
	public int deleteScrapBook(Map<String,Integer> map);
}