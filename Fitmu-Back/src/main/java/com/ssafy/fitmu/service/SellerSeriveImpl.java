package com.ssafy.fitmu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.fitmu.dao.SellerDao;
import com.ssafy.fitmu.dto.Product;
import com.ssafy.fitmu.dto.Seller;

@Service
public class SellerSeriveImpl implements SellerService {

	private SellerDao sellerDao;
	
	public SellerSeriveImpl (SellerDao sellerDao) {
		this.sellerDao = sellerDao;
	}
	
	@Override
	public int insertSeller(Seller seller) {
		int userId = seller.getUserId();
		//유저정보에서 롤 변경
		sellerDao.updateRole(userId);
		return sellerDao.insertSeller(seller);
	}

	@Override
	public int insertProduct(Product product) {
		return sellerDao.insertProduct(product);
	}

	@Override
	public int insertAnswer(int inquiryId, String answer) {
		Map<String, String> map = new HashMap<>();
		map.put("inquiryId", Integer.toString(inquiryId));
		map.put("answer", answer);
		return sellerDao.insertAnswer(map);
	}

	@Override
	public int deleteAnswer(int inquiryId) {
		return sellerDao.deleteAnswer(inquiryId);
	}

	@Override
	public Seller getSellerByUserId(int userId) {
		return sellerDao.getSellerByUserId(userId);
	}

	@Override
	public List<Seller> selectAll() {
		return sellerDao.selectAll();
	}

}
