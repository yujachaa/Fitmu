package com.ssafy.fitmu.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitmu.dto.Comment;
import com.ssafy.fitmu.dto.Inquiry;
import com.ssafy.fitmu.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/comment-api")
@Tag(name = "댓글 컨트롤러")
public class CommentController {
	private CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	// 댓글 상세 조회 -> 테스트용
	@GetMapping("/{commentId}")
	@Operation(summary = "댓글 상세 조회")
	public ResponseEntity<?> commentDetail(@PathVariable("commentId") int id) {
		Comment comment= commentService.commentDetail(id);

		if (comment == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Comment>(comment, HttpStatus.OK);
		}
	}

	// 댓글 전체 조회 -> 게시글 기준
	@GetMapping("/story/{storyId}")
	@Operation(summary = "댓글 전체 조회(게시글 기준)")
	public ResponseEntity<?> getCommentByStory(@PathVariable("storyId") int id){
		List<Comment> list = commentService.selectAllByStory(id);
		
		if (list == null || list.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<Comment>>(list, HttpStatus.OK);
		}
	}
	
	
	// 댓글 등록
	@PostMapping("/regist")
	@Operation(summary = "댓글 등록")
	public ResponseEntity<?> commentRegist(@RequestBody Comment comment) {
		int result = commentService.registComment(comment);

		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			//리뷰 등록 완료했으면 상품에 별점 업데이트
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	// 댓글 삭제
	@DeleteMapping("/{commentId}")
	@Operation(summary = "댓글 삭제")
	public ResponseEntity<?> commentDelete(@PathVariable("commentId") int id) {
		
		int result = commentService.deleteComment(id);

		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

}
