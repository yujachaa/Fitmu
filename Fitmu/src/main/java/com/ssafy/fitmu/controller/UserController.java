package com.ssafy.fitmu.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitmu.dto.SearchCondition;
import com.ssafy.fitmu.dto.User;
import com.ssafy.fitmu.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@CrossOrigin("*")
@RequestMapping("/user-api")
@Tag(name = "123")
public class UserController {
	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	// 로그인
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User loginUser){
		// DB에서 로그인 정보 확인하고 확인 됐으면 Session에 등록?
	}
	
	// 회원가입
	@PostMapping("/regist")
	public ResponseEntity<?> regist(@RequestBody User registUser){
		
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public ResponseEntity<?> logout(){
		// 그냥 session 지우기?
	}
	
	// 회원탈퇴
	@DeleteMapping("/withdrawal")
	public ResponseEntity<?> withdrawal(){
		// session에 정보 사용해서 탈퇴하고 session 비우기
	}
	
	// 유저 검색을 그냥 프론트에서 다 하면..? 이게 더 편할거같기도하고..
	@GetMapping("/search")
	public ResponseEntity<?> searchByCondition(@ModelAttribute SearchCondition condition){
		
	}
	
	// 설정변경
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody User user){
		
	}
	
	// 팔로우 등록
	@PostMapping("/follow/{followingId}")
	public ResponseEntity<?> registFollow(@PathVariable("followingId") int followingId){
		// 여기를 이렇게 해야되나..? 세션에서 userId 가져와도 되나?
	}
	
	// 팔로우 삭제
	@DeleteMapping("/follow/{followingId}")
	public ResponseEntity<?> deleteFollow(@PathVariable("followingId") int followingId){
		
	}
	
	// 팔로우 조회
	@GetMapping("/follower")
	public  ResponseEntity<?> getFollower(){
		// session에 있는 user정보 가지고 잘 가져오면 될거같기도 하고..
	}
	
	// 팔로잉 조회
	@GetMapping("/followee")
	public  ResponseEntity<?> getFollowee(){
		
	}
	
	

}
