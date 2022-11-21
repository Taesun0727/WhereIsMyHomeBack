package com.mvc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mvc.vo.House;
import com.mvc.vo.Interest;

@Mapper
public interface InterestMapper {
	void InterestInsert(Interest interest);
	void InterestDelete(Interest interest);
	Interest getInterest(Interest interest);
	List<House> InterestSearchAll(String userinfo_num);
}
