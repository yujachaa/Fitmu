package com.ssafy.fitmu.dao;

import java.util.List;
import java.util.Map;

import com.ssafy.fitmu.dto.Inquiry;
import com.ssafy.fitmu.dto.Review;

public interface InquiryDao {
	//문의 상세 조회
	public Inquiry selectOne(int inquiryId);
	//문의 전체 조회 -> 사용자 기준
	public List<Inquiry> selectAllByUser(int userId);
	//문의 전체 조회 -> 제품 기준
	public List<Inquiry> selectAllByProduct(int productId);
	//문의 등록
	public int insertInquiry(Inquiry inquiry);
	//문의 삭제
	public int deleteInquiry(int inquiryId);
}
