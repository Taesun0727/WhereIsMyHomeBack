package com.mvc.service;

import java.util.ArrayList;

import com.mvc.vo.House;

public interface HouseService {
	ArrayList<String> siSearch();
	ArrayList<String> gugunSearch(String sido);
	ArrayList<String> dongSearch(String gugun);
	ArrayList<House> dongSelect(String dong);
	ArrayList<House> houseSearch(String apart);
	House getHouse(String aptCode);
}
