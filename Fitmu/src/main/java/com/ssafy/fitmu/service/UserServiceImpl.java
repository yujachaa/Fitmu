package com.ssafy.fitmu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.fitmu.dao.UserDao;
import com.ssafy.fitmu.dto.SearchCondition;
import com.ssafy.fitmu.dto.User;

public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User selectOne(int id) {
		return userDao.selectOne(id);
	}
	
	@Override
	public User selectOneByEmail(String email) {
		return userDao.selectOneByEmail(email);
	}

	@Override
	public List<User> selectAll() {
		return userDao.selectAll();
	}

	@Override
	public int insertUser(User user) {
		return userDao.insertUser(user);
	}

	@Override
	public int deleteUser(int id) {
		return userDao.deleteUser(id);
	}

	@Override
	public List<Integer> getFollowerOfUser(int id) {
		return userDao.getFollowerOfUser(id);
	}

	@Override
	public int updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public List<Integer> getFolloweeOfUser(int id) {
		return userDao.getFolloweeOfUser(id);
	}

	@Override
	public List<User> searchByCondition(SearchCondition condition) {
		return userDao.searchByCondition(condition);
	}

	@Override
	public int insertFollow(int userId, int followingId) {
		Map<String, Integer> map = new HashMap<>();
		map.put("userId", userId);
		map.put("followingId", followingId);
		return userDao.insertFollow(map);
	}

	@Override
	public int deleteFollow(int userId, int followingId){
		Map<String, Integer> map = new HashMap<>();
		map.put("userId", userId);
		map.put("followingId", followingId);
		return userDao.deleteFollow(map);
	}
}
