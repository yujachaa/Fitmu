package com.ssafy.fitmu.dao;

import java.util.List;
import java.util.Map;

import com.ssafy.fitmu.dto.SearchCondition;
import com.ssafy.fitmu.dto.User;

public interface UserDao {
	public User selectOne(int id);
	public User selectOneByEmail(String email);
	public List<User> selectAll();
	public int insertUser(User user);
	public int deleteUser(int id);
	public int insertFollow(Map<String, Integer> idMap);
	public int deleteFollow(Map<String, Integer> idMap);
	public List<Integer> getFollowerOfUser(int id);
	public List<Integer> getFolloweeOfUser(int id);
	public int updateUser(User user);
	public List<User> searchByCondition(SearchCondition condition);
	public List<Integer> getProductScrap(int userId);
	public List<Integer> getStoryScrap(int userId);
	
}
