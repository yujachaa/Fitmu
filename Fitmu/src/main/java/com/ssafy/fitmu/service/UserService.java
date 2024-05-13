package com.ssafy.fitmu.service;

import java.util.List;

import com.ssafy.fitmu.dto.User;

public interface UserService {
	public User selectOne(int id);
	public List<User> selectAll();
	public int insertUser(User user);
	public int deleteUser(User user);
	public List<Integer> getUserFollower(int id);
	public List<Integer> getUserFollowee(int id);
	public int updateUser(User user);
}
