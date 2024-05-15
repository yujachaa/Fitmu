package com.ssafy.fitmu.dao;

import java.util.List;

import com.ssafy.fitmu.dto.Comment;

public interface CommentDao {
	// 댓글 상세조회 -> 필요는 없을거같은데 테스트용으로 만듬
	public Comment selectOne(int commentId);
	// 게시글 기준 전체 댓글 조회
	public List<Comment> selectAllByStory(int storyId);
	//댓글 등록
	public int insertComment(Comment comment);
	//댓글 삭제
	public int deleteComment(int commentId);
}
