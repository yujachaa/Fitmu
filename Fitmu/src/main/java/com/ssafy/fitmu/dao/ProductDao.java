package com.ssafy.fitmu.dao;

import java.util.List;
import java.util.Map;

import com.ssafy.fitmu.dto.Product;
import com.ssafy.fitmu.dto.SearchCondition;

public interface ProductDao {
	//상세 조회
	public Product selectOne(int productId);
	//전체 조회
	public List<Product> selectAll();
	//컨디션 조회
	public List<Product> searchByCondition(SearchCondition condition);
	//할인율 조회 -> 할인율 discount 이상인 것들 내림차순으로 조회
	public List<Product> getDiscountList(int discount);
	//제품 스크랩 등록
	public int insertProductScrap(Map<String, Integer> map);
	//제품 스크랩 삭제
	public int deleteProductScrap(Map<String, Integer> map);
}
