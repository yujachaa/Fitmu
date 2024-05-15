package com.ssafy.fitmu.service;

import java.util.List;

import com.ssafy.fitmu.dto.Inquiry;

public interface InquiryService {
	//문의 상세조회
	public Inquiry inquiryDetail(int id);
	//사용자 기준 문의 전체 조회 -> 최신순정렬
	public List<Inquiry> selectAllByUser(int userId);
	//제품 기준 문의 전체 조회
	public List<Inquiry> selectAllByProduct(int productId);
	//문의 등록
	public int registInquiry(Inquiry inquiry);
	//문의 삭제
	public int deleteInquiry(int inquiryId);
}
