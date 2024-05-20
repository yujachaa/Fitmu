package com.ssafy.fitmu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.fitmu.dao.StoryDao;
import com.ssafy.fitmu.dto.SearchCondition;
import com.ssafy.fitmu.dto.Story;
import com.ssafy.fitmu.dto.StoryImage;

@Service
public class StoryServiceImpl implements StoryService{
	private StoryDao storyDao;

	public StoryServiceImpl(StoryDao storyDao) {
		this.storyDao = storyDao;
	}

	@Override
	public int insertStory(Story story) {
		return storyDao.insertStory(story);
	}

	@Override
	public int updateStory(Story story) {
		return storyDao.updateStory(story);
	}

	@Override
	public int deleteStory(int storyId) {
		return storyDao.deleteStory(storyId);
	}

	@Override
	public List<Story> selectAll() {
		return storyDao.selectAll();
	}

	@Override
	public List<Story> searchStory(SearchCondition condition) {
		return storyDao.searchStory(condition);
	}

	@Override
	public List<Story> selectByUserId(int userId) {
		return storyDao.selectByUserId(userId);
	}

	@Override
	public List<Story> popularStory() {
		return storyDao.popularStory();
	}

	@Override
	public List<Story> latestStory() {
		return storyDao.latestStory();
	}

	@Override
	public Story selectOne(int storyId) {
		storyDao.updateViewCnt(storyId);
		return storyDao.selectOne(storyId);
	}

	@Override
	public int increaseLikeCnt(int storyId) {
		return storyDao.increaseLikeCnt(storyId);
	}

	@Override
	public int decreaseLikeCnt(int storyId) {
		return storyDao.decreaseLikeCnt(storyId);
	}

	@Override
	public int insertScrapBook(int userId, int storyId) {
		Map<String, Integer> map = new HashMap<>();
		map.put("userId", userId);
		map.put("storyId", storyId);
		return storyDao.insertScrapBook(map);
	}

	@Override
	public int deleteScrapBook(int userId, int storyId) {
		Map<String, Integer> map = new HashMap<>();
		map.put("userId", userId);
		map.put("storyId", storyId);
		return storyDao.deleteScrapBook(map);
	}

	@Override
	public List<Story> likeStory() {
		return storyDao.likeStory();
	}

	@Override
	public int insertStoryImage(StoryImage storyImage) {
		return storyDao.insertStoryImage(storyImage);
	}

	@Override
	public List<StoryImage> getImageByStoryId(int storyId) {
		return storyDao.getImageByStoryId(storyId);
	}

	@Override
	public int getScrapCountByStoryId(int storyId) {
		return storyDao.getScrapCountByStoryId(storyId);
	}

}
