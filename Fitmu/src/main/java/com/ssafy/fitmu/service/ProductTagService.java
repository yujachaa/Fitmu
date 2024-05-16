package com.ssafy.fitmu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.fitmu.dto.ProductTag;

@Service
public interface ProductTagService {
	//태그 전체 조회 -> story id 기준
	public List<ProductTag> seletAllByStroy(int storyId);
	//태그 1개 조회
	public ProductTag selectOne(int tagId);
	//태그 등록
	public int insertTag(ProductTag productTag);
	//태그 전체 삭제 -> story id 기준
	public int deleteAllTagOfStory(int storyId);
}
