package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.mapper.InterestMapper;
import com.mvc.vo.Interest;

@Service
public class InterestServiceImpl implements InterestService {
	
	@Autowired
	InterestMapper mapper;

	@Override
	public void InterestInsert(Interest interest) {
		mapper.InterestInsert(interest);
	}

	@Override
	public void InterestDelete(int interest_num) {
		mapper.InterestDelete(interest_num);
	}

	@Override
	public List<Interest> InterestSearch(String userinfo_id) {
		return null;
	}

}
