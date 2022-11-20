package com.mvc.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.mvc.vo.House;

@Mapper
public interface HouseMapper {
	ArrayList<String> siSearch();
	ArrayList<String> gugunSearch(String sido);
	ArrayList<String> dongSearch(String gugun);
	ArrayList<House> dongSelect(String dong);
	ArrayList<House> houseSearch(String apart);
	House dongInfoSearch(String dong);
}
