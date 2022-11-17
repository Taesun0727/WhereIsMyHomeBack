package com.mvc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mvc.vo.Interest;

@Mapper
public interface InterestMapper {
	void InterestInsert(Interest interest);
	void InterestDelete(int interest_num);
	List<Interest> InterestSearch(String userinfo_id);
}
