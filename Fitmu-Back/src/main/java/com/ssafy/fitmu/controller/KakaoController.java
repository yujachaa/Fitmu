package com.ssafy.fitmu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitmu.dto.User;
import com.ssafy.fitmu.service.KakaoLoginService;
import com.ssafy.fitmu.service.UserService;
import com.ssafy.fitmu.util.JwtUtil;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin("*")
@RequestMapping("/kakao-api")
@Tag(name = "카카오 컨트롤러")
public class KakaoController {
	private final KakaoLoginService kakaoLoginService;
	private final UserService userService;
	private final JwtUtil jwtUtil;
	
	public KakaoController(KakaoLoginService kakaoLoginService, UserService userService, JwtUtil jwtUtil) {
		this.kakaoLoginService = kakaoLoginService;
		this.userService = userService;
		this.jwtUtil = jwtUtil;
	}
	
	@GetMapping("/kakao-login")
	public ResponseEntity<?> kakaoLogin(@RequestParam("code") String code, HttpSession session){
		System.out.println(code);
		String kakaoToken = kakaoLoginService.getKakaoAccessToken(code);
		User user = kakaoLoginService.createKakaoUser(kakaoToken);
		Map<String,Object> result = new HashMap<>();
		
		if(user != null) {
			List<User> users = userService.selectAll();
			
			for(User DBuser : users) {
				if(DBuser.getEmail().equals(user.getEmail())) {
					result.put("message", "성공");
					result.put("access-token", jwtUtil.createToken(user.getPassword()));
					result.put("userId", DBuser.getUserId());
					result.put("role", DBuser.getRole());
					
					break;
				}
			}
			int loginresult = userService.insertUser(user);
			if(loginresult == 0) {
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
		}else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}

}
