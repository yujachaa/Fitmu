package com.ssafy.fitmu.service;

import com.ssafy.fitmu.dto.Product;
import com.ssafy.fitmu.dto.Seller;

public interface SellerService {
	//판매자 등록
	public int insertSeller(Seller seller);
	//상품 등록
	public int insertProduct(Product product);
	//문의 답변 등록 + 수정
	public int insertAnswer(int inquiryId, String answer);
	//문의 답변 삭제
	public int deleteAnswer(int inquiryId);
	
	public Seller getSellerByUserId(int userId);
}
