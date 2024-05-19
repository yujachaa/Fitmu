package com.ssafy.fitmu.service;

import com.ssafy.fitmu.dto.User;

public interface KakaoLoginService {
	public String getKakaoAccessToken(String code);
	public User createKakaoUser(String token);
	public int kakaoLogin(User user);
}
