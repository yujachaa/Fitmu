package com.ssafy.fitmu.dao;

import java.util.List;
import java.util.Map;

import com.ssafy.fitmu.dto.Product;
import com.ssafy.fitmu.dto.Seller;

public interface SellerDao {
	//판매자 등록
	public int insertSeller(Seller seller);	
	//유저테이블에서 롤 변경
	public int updateRole(int userId);
	//상품 등록
	public int insertProduct(Product product);
	//문의 답변 등록 + 수정 -> put으로 Inquiry.answer 만 수정
	public int insertAnswer(Map<String, String> answerMap);
	//문의 답변 삭제
	public int deleteAnswer(int inquiryId);
	// 유저아이디로 검색
	public Seller getSellerByUserId(int userId);
	public List<Seller> selectAll();
}
