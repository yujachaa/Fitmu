package com.ssafy.fitmu.service;

import java.util.List;

import com.ssafy.fitmu.dao.UserDao;
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
	public int deleteUser(User user) {
		return userDao.deleteUser(user);
	}

	@Override
	public List<Integer> getUserFollower(int id) {
		return userDao.getUserFollower(id);
	}

	@Override
	public int updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public List<Integer> getUserFollowee(int id) {
		return userDao.getUserFollowee(id);
	}


}
