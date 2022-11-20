package com.mvc.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.mapper.HouseMapper;
import com.mvc.vo.House;

@Service
public class HouseServiceImpl implements HouseService {

	@Autowired
	HouseMapper mapper;
	
	@Override
	public ArrayList<String> siSearch() {
		return mapper.siSearch();
	}

	@Override
	public ArrayList<String> gugunSearch(String sido) {
		return mapper.gugunSearch(sido);
	}

	@Override
	public ArrayList<String> dongSearch(String gugun) {
		return mapper.dongSearch(gugun);
	}

	@Override
	public ArrayList<House> dongSelect(String dong) {
		return mapper.dongSelect(dong);
	}

	@Override
	public ArrayList<House> houseSearch(String apart) {
		return mapper.houseSearch(apart);
	}

	@Override
	public House getHouse(String aptCode) {
		return mapper.getHouse(aptCode);
	}

	@Override
	public ArrayList<House> getDeal(String aptCode) {
		return mapper.getDeal(aptCode);
	}


}
