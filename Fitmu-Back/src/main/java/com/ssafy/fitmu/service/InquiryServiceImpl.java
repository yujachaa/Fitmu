package com.ssafy.fitmu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.fitmu.dao.InquiryDao;
import com.ssafy.fitmu.dto.Inquiry;

@Service
public class InquiryServiceImpl implements InquiryService {
	
	private InquiryDao inquiryDao;
	
	public InquiryServiceImpl(InquiryDao inquiryDao) {
		this.inquiryDao = inquiryDao;
	}

	@Override
	public Inquiry inquiryDetail(int id) {
		return inquiryDao.selectOne(id);
	}

	@Override
	public List<Inquiry> selectAllByUser(int userId) {
		return inquiryDao.selectAllByUser(userId);
	}

	@Override
	public List<Inquiry> selectAllByProduct(int productId) {
		return inquiryDao.selectAllByProduct(productId);
	}

	@Override
	public int registInquiry(Inquiry inquiry) {
		return inquiryDao.insertInquiry(inquiry);
	}

	@Override
	public int deleteInquiry(int inquiryId) {
		return inquiryDao.deleteInquiry(inquiryId);
	}



}
