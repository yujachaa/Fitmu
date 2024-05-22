package com.ssafy.fitmu.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitmu.dto.Product;
import com.ssafy.fitmu.dto.SearchCondition;
import com.ssafy.fitmu.dto.Story;
import com.ssafy.fitmu.dto.User;
import com.ssafy.fitmu.service.ProductService;
import com.ssafy.fitmu.service.StoryService;

import com.ssafy.fitmu.service.KakaoLoginService;
import com.ssafy.fitmu.service.UserService;
import com.ssafy.fitmu.util.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user-api")
@Tag(name = "유저 컨트롤러")
public class UserController {
	
	private final UserService userService;
	private final ProductService productService;
	private final StoryService storyService;
	private final JwtUtil jwtUtil;


	public UserController(UserService userService, ProductService productService, StoryService storyService, JwtUtil jwtUtil) {
		this.userService = userService;
		this.productService = productService;
		this.storyService = storyService;
		this.jwtUtil = jwtUtil;
	}
	
	// 전체 유저 조회
	@GetMapping("/")
	@Operation(summary = "사용자 전체 조회")
	public ResponseEntity<?> getAllUsers(){
		List<User> list = userService.selectAll();
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	
	//유저 1명 조회
	@GetMapping("/{userId}")
	@Operation(summary = "사용자 상세 조회")
	public ResponseEntity<?> getUser(@PathVariable("userId") int userId){
		User user = userService.selectOne(userId);
		
		if(user == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
	}

	// 로그인 (프론트 sessionstorage에 로그인 된 유저의 userId 넣기, 데이터 가져올 수 있게)
	@PostMapping("/login")
	@Operation(summary = "로그인")
	public ResponseEntity<?> login(HttpSession session, @RequestBody User loginUser) {
		User DBuser = userService.selectOneByEmail(loginUser.getEmail());
		
		HttpStatus status = null;
		Map<String,Object> result = new HashMap<>();
		
		if(DBuser != null && DBuser.getPassword().equals(jwtUtil.createToken(loginUser.getPassword()))) {			
			// 토큰 만들어서 줘야돼
			result.put("message", "성공");
			result.put("access-token", jwtUtil.createToken(loginUser.getPassword()));
			result.put("userId", DBuser.getUserId());
			result.put("role", DBuser.getRole());
			session.setAttribute("loginUser", DBuser);
			status = HttpStatus.OK;
		}else {
			result.put("message", "실패");
			status = HttpStatus.BAD_REQUEST;
		}
		
		return new ResponseEntity<Map<String, Object>>(result, status);
	}

	// 회원가입
	@PostMapping("/regist")
	@Operation(summary = "회원가입")
	public ResponseEntity<?> regist(@RequestBody User registUser) {

		String token = jwtUtil.createToken(registUser.getPassword());
		registUser.setPassword(token);
		
		int result = userService.insertUser(registUser);

		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}
	
	//이메일 중복확인
	@PostMapping("/email")
	@Operation(summary = "이메일 중복확인")
	public ResponseEntity<?> emailCheck(@RequestParam("email") String email){
				
		List<User> allUsers = userService.selectAll();
		
		for (User user : allUsers) {
			if (user.getEmail().equals(email)) {
				return new ResponseEntity<Void>(HttpStatus.IM_USED);
			}
		}
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	

	// 로그아웃 (여기서도 지우고, sessionstorage에서도 지우기)
	@GetMapping("/logout")
	@Operation(summary = "로그아웃")
	public ResponseEntity<?> logout(HttpSession session) {
		
		// 그냥 session 지우기?
		session.invalidate();

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 회원탈퇴
	@DeleteMapping("/withdrawal")
	@Operation(summary = "회원탈퇴")
	public ResponseEntity<?> withdrawal(HttpSession session) {
		// session에 정보 사용해서 탈퇴하고 session 비우기
		User loginUser = (User) session.getAttribute("loginUser");
		int result = userService.deleteUser(loginUser.getUserId());

		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			session.invalidate();
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	// 유저 검색
	@GetMapping("/search")
	@Operation(summary = "사용자 검색")
	public ResponseEntity<?> searchByCondition(@ModelAttribute SearchCondition condition) {
		List<User> userList = userService.searchByCondition(condition);

		if (userList == null || userList.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
		}
	}

	// 설정변경
	@PutMapping("/update")
	@Operation(summary = "사용자 설정 변경")
	public ResponseEntity<?> update(HttpSession session, @RequestBody User user) {
//		User loginUser = (User) session.getAttribute("loginUser");
//		int loginUserId = loginUser.getUserId();
//
//		user.setUserId(loginUserId);
//		System.out.println(user);
		int result = userService.updateUser(user);

		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	// 팔로워 등록
	@PostMapping("/user/{userId}/follow/{followingId}")
	@Operation(summary = "사용자 팔로워 등록(사용자가 등록)")
	public ResponseEntity<?> registFollower(HttpSession session, @PathVariable("userId") int userId, @PathVariable("followingId") int followingId) {

		int result = userService.insertFollow(userId, followingId);

		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	// 팔로워 삭제
	@DeleteMapping("/user/{userId}/follow/{followingId}")
	@Operation(summary = "사용자 팔로워 삭제")
	public ResponseEntity<?> deleteFollower(HttpSession session, @PathVariable("userId") int userId, @PathVariable("followingId") int followingId) {

		int result = userService.deleteFollow(userId, followingId);

		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	// 팔로워 조회(유저가 팔로우 하고 있는 사람들)
	@GetMapping("/user/{userId}/follower")
	@Operation(summary = "유저가 팔로우 하고 있는 사람들")
	public ResponseEntity<?> getFollower(HttpSession session, @PathVariable("userId") int id) {
		// session에 있는 user정보 가지고 잘 가져오면 될거같기도 하고..-> 다른 유저의 팔로잉, 팔로워 보기 위해서 그냥 경로에 유저아이디 넣어주는 걸로!!
		
		List<Integer> followerList = userService.getFollowerOfUser(id);
		
		if(followerList == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}else {
			List<User> list = new ArrayList<>();
			
			for(int i = 0 ;i < followerList.size(); i++) {
				int userId = followerList.get(i);
				list.add(userService.selectOne(userId));
			}
			return new ResponseEntity<List<User>>(list, HttpStatus.OK);
		}
	}

	// 팔로잉 조회(유저를 팔로우 하고 있는 사람들)
	@GetMapping("/user/{userId}/followee")
	@Operation(summary = "유저를 팔로우 하고 있는 사람들")
	public ResponseEntity<?> getFollowee(HttpSession session, @PathVariable("userId") int id) {
		
		List<Integer> followeeList = userService.getFolloweeOfUser(id);
		
		if(followeeList == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}else {
			List<User> list = new ArrayList<>();
			
			for(int i = 0 ;i < followeeList.size(); i++) {
				int userId = followeeList.get(i);
				list.add(userService.selectOne(userId));
			}
			return new ResponseEntity<List<User>>(list, HttpStatus.OK);
		}
	}
	
	//유저 상품 스크랩북 조회 -> 상품객체배열로 리턴
	@GetMapping("/user/{userId}/product-scrap")
	@Operation(summary = "유저의 상품 스크랩북 조회")
	public ResponseEntity<?> getProductScrapbook(@PathVariable("userId") int id) {
		
		List<Integer> productList = userService.getProductScrap(id);
		
		if(productList == null ) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}else {
			List<Product> list = new ArrayList<>();
			
			for(int i = 0 ;i < productList.size(); i++) {
				int productId = productList.get(i);
				list.add(productService.selectOne(productId));
			}
			return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		}
	}
	//유저 게시글 스크랩북 조회 -> 게시글객체배열로 리턴
	@GetMapping("/user/{userId}/story-scrap")
	@Operation(summary = "유저의 게시글 스크랩북 조회")
	public ResponseEntity<?> getStoryScrapbook(@PathVariable("userId") int id) {
		
		List<Integer> storyList = userService.getStoryScrap(id);
				
		if(storyList == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}else {
			List<Story> list = new ArrayList<>();
			
			for(int i = 0 ;i < storyList.size(); i++) {
				int storyId = storyList.get(i);
				list.add(storyService.selectOne(storyId));
			}
			return new ResponseEntity<List<Story>>(list, HttpStatus.OK);
		}
	}
}
