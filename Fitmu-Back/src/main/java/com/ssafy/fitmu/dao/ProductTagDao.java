package com.ssafy.fitmu.dao;

import java.util.List;

import com.ssafy.fitmu.dto.ProductTag;

public interface ProductTagDao {
	//태그 전체 조회 -> story id 기준
	public List<ProductTag> seletAllByStroy(int storyId);
	//태그 1개 조회
	public ProductTag selectOne(int tagId);
	//태그 등록
	public int insertTag(ProductTag productTag);
	//태그 전체 삭제 -> story id 기준
	public int deleteAllTagOfStory(int storyId);
}
