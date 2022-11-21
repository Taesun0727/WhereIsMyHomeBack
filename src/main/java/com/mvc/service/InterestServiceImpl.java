package com.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.mapper.InterestMapper;
import com.mvc.vo.House;
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
	public void InterestDelete(Interest interest) {
		mapper.InterestDelete(interest);
	}

	@Override
	public Interest getInterest(Interest interest) {
		// TODO Auto-generated method stub
		return mapper.getInterest(interest);
	}

	@Override
	public List<House> interestSearchAll(String userinfo_num) {
		// TODO Auto-generated method stub
		return mapper.InterestSearchAll(userinfo_num);
	}

}
