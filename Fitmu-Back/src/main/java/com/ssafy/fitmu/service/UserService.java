package com.ssafy.fitmu.service;

import java.util.List;

import com.ssafy.fitmu.dto.SearchCondition;
import com.ssafy.fitmu.dto.User;

public interface UserService {
	public User selectOne(int id);
	public User selectOneByEmail(String email);
	public List<User> selectAll();
	public int insertUser(User user);
	public int deleteUser(int id);
	public int insertFollow(int userId, int followerId);
	public int deleteFollow(int userId, int followerId);
	public List<Integer> getFollowerOfUser(int id);
	public List<Integer> getFolloweeOfUser(int id);
	public int updateUser(User user);
	public List<User> searchByCondition(SearchCondition condition);
}
