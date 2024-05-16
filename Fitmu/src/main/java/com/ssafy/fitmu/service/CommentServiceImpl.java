package com.ssafy.fitmu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.fitmu.dao.CommentDao;
import com.ssafy.fitmu.dto.Comment;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentDao commentDao;
	
	@Override
	public Comment commentDetail(int id) {
		return commentDao.selectOne(id);
	}

	@Override
	public List<Comment> selectAllByStory(int storyId) {
		return commentDao.selectAllByStory(storyId);
	}

	@Override
	public int registComment(Comment comment) {
		return commentDao.insertComment(comment);
	}

	@Override
	public int deleteComment(int commentId) {
		return commentDao.deleteComment(commentId);
	}

}
