package com.ssafy.fitmu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.fitmu.dao.ProductDao;
import com.ssafy.fitmu.dto.Product;
import com.ssafy.fitmu.dto.ProductImage;
import com.ssafy.fitmu.dto.SearchCondition;

@Service
public class ProductServiceImpl implements ProductService {
	
	private ProductDao productDao;
	
	public ProductServiceImpl(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	@Override
	public Product selectOne(int productId) {
		return productDao.selectOne(productId);
	}

	@Override
	public List<Product> selectAll() {
		return productDao.selectAll();
	}

	@Override
	public List<Product> searchByCondition(SearchCondition condition) {
		return productDao.searchByCondition(condition);
	}

	@Override
	public List<Product> getDiscountList(int discount) {
		return productDao.getDiscountList(discount);
	}

	@Override
	public int insertProductScrap(int userId, int productId) {
		Map<String, Integer> map = new HashMap<>();
		map.put("user_id", userId);
		map.put("product_id", productId);
		return productDao.insertProductScrap(map);
	}

	@Override
	public int deleteProductScrap(int userId, int productId) {
		Map<String, Integer> map = new HashMap<>();
		map.put("user_id", userId);
		map.put("product_id", productId);
		return productDao.deleteProductScrap(map);
	}

	@Override
	public int insertProductImage(ProductImage productImage) {
		return productDao.insertProductImage(productImage);
	}

	@Override
	public List<ProductImage> getImageByProductId(int productId) {
		return productDao.getImageByProductId(productId);
	}

}
