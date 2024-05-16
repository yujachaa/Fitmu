package com.ssafy.fitmu.service;

import java.util.List;

import com.ssafy.fitmu.dao.ProductTagDao;
import com.ssafy.fitmu.dto.ProductTag;

public class ProductTagServiceImpl implements ProductTagService {
	
	private ProductTagDao productTagDao;
	
	public ProductTagServiceImpl(ProductTagDao productTagDao) {
		this.productTagDao = productTagDao;
	}
	
	@Override
	public List<ProductTag> seletAllByStroy(int storyId) {
		return productTagDao.seletAllByStroy(storyId);
	}

	@Override
	public ProductTag selectOne(int tagId) {
		return productTagDao.selectOne(tagId);
	}

	@Override
	public int insertTag(ProductTag productTag) {
		return productTagDao.insertTag(productTag);
	}

	@Override
	public int deleteAllTagOfStory(int storyId) {
		return productTagDao.deleteAllTagOfStory(storyId);
	}

}
