package com.ssafy.fitmu.service;

import java.util.List;

import com.ssafy.fitmu.dto.Product;
import com.ssafy.fitmu.dto.ProductImage;
import com.ssafy.fitmu.dto.SearchCondition;

public interface ProductService {
	//상세조회
	public Product selectOne(int productId);
	//전체조회
	public List<Product> selectAll();
	//컨디션 조회
	public List<Product> searchByCondition(SearchCondition condition);
	//할인율 조회 -> 할인율 discount 이상인 것들 내림차순으로 조회
	public List<Product> getDiscountList(int discount);
	//제품 스크랩 등록
	public int insertProductScrap(int userId, int productId);
	//제품 스크랩 삭제
	public int deleteProductScrap(int userId, int productId);
	//제품 사진 등록
	public int insertProductImage(ProductImage productImage);
	
	public List<ProductImage> getImageByProductId(int productId);
	
	public int insertProduct(Product product);
	
	public List<ProductImage> getProductImage();
}
