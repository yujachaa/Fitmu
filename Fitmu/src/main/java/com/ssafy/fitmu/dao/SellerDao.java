package com.ssafy.fitmu.dao;

import com.ssafy.fitmu.dto.Product;
import com.ssafy.fitmu.dto.Seller;

public interface SellerDao {
	//판매자 등록
	public int insertSeller(Seller seller);	
	//상품 등록
	public int insertProduct(Product product);
	//문의 답변 등록 -> put으로 Inquiry.answer 만 수정
	public int insertAnswer(String answer);
	//문의 답변 수정 -> put으로 Inquiry.answer 수정
	public int updateAnswer(String answer);
	//문의 답변 삭제
	public int deleteAnswer(int inquiryId);
}
