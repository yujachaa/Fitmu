package com.ssafy.fitmu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitmu.dto.SearchCondition;
import com.ssafy.fitmu.dto.User;
import com.ssafy.fitmu.service.UserService;
import com.ssafy.fitmu.util.JwtUtil;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin("*")
@RequestMapping("/user-api")
@Tag(name = "유저 컨트롤러")
public class UserController {
	private final UserService userService;
	private final JwtUtil jwtUtil;

	public UserController(UserService userService, JwtUtil jwtUtil) {
		this.userService = userService;
		this.jwtUtil = jwtUtil;
	}
	
	// 전체 유저 조회
	@GetMapping("/")
	public ResponseEntity<?> getAllUsers(){
		List<User> list = userService.selectAll();
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

	// 로그인 (프론트 sessionstorage에 로그인 된 유저의 userId 넣기, 데이터 가져올 수 있게)
	@PostMapping("/login")
	public ResponseEntity<?> login(HttpSession session, @RequestBody User loginUser) {
		User DBuser = userService.selectOneByEmail(loginUser.getEmail());
		
		HttpStatus status = null;
		Map<String,Object> result = new HashMap<>();
		
		if(DBuser != null && DBuser.getPassword().equals(jwtUtil.createToken(loginUser.getPassword()))) {			
			// 토큰 만들어서 줘야돼
			result.put("message", "성공");
			result.put("access-token", jwtUtil.createToken(loginUser.getPassword()));
			result.put("userId", DBuser.getUserId());
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
	public ResponseEntity<?> regist(@RequestBody User registUser) {
		List<User> allUsers = userService.selectAll();

		for (User user : allUsers) {
			if (user.getEmail() == registUser.getEmail()) {
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
		}
		String token = jwtUtil.createToken(registUser.getPassword());
		registUser.setPassword(token);
		
		int result = userService.insertUser(registUser);

		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	// 로그아웃 (여기서도 지우고, sessionstorage에서도 지우기)
	@GetMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session) {
		// 그냥 session 지우기?
		session.invalidate();

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// 회원탈퇴
	@DeleteMapping("/withdrawal")
	public ResponseEntity<?> withdrawal(HttpSession session) {
		// session에 정보 사용해서 탈퇴하고 session 비우기
		User loginUser = (User) session.getAttribute("loginUser");
		int result = userService.deleteUser(loginUser.getUserId());

		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	// 유저 검색
	@GetMapping("/search")
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
	public ResponseEntity<?> update(HttpSession session, @RequestBody User user) {
		User loginUser = (User) session.getAttribute("loginUser");
		int loginUserId = loginUser.getUserId();

		user.setUserId(loginUserId);

		int result = userService.insertUser(user);

		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	// 팔로워 등록
	@PostMapping("/follow/{followingId}")
	public ResponseEntity<?> registFollower(HttpSession session, @PathVariable("followingId") int followingId) {
		User loginUser = (User) session.getAttribute("loginUser");
		int loginUserId = loginUser.getUserId();

		int result = userService.insertFollow(loginUserId, followingId);

		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	// 팔로워 삭제
	@DeleteMapping("/follow/{followingId}")
	public ResponseEntity<?> deleteFollower(HttpSession session, @PathVariable("followingId") int followingId) {
		User loginUser = (User) session.getAttribute("loginUser");
		int loginUserId = loginUser.getUserId();

		int result = userService.deleteFollow(loginUserId, followingId);

		if (result == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	// 팔로워 조회(유저가 팔로우 하고 있는 사람들)
	@GetMapping("/follower")
	public ResponseEntity<?> getFollower(HttpSession session) {
		// session에 있는 user정보 가지고 잘 가져오면 될거같기도 하고..
		User loginUser = (User)session.getAttribute("loginUser");
		int loginUserId = loginUser.getUserId();
		
		List<Integer> followerList = userService.getFollowerOfUser(loginUserId);
		
		if(followerList == null || followerList.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<List<Integer>>(followerList, HttpStatus.OK);
		}
	}

	// 팔로잉 조회(유저를 팔로우 하고 있는 사람들)
	@GetMapping("/followee")
	public ResponseEntity<?> getFollowee(HttpSession session) {
		User loginUser = (User)session.getAttribute("loginUser");
		int loginUserId = loginUser.getUserId();
		
		List<Integer> followeeList = userService.getFolloweeOfUser(loginUserId);
		
		if(followeeList == null || followeeList.size() == 0) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}else {
			return new ResponseEntity<List<Integer>>(followeeList, HttpStatus.OK);
		}
	}

}
