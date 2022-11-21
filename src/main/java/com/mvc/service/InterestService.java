package com.mvc.service;

import java.util.List;

import com.mvc.vo.House;
import com.mvc.vo.Interest;

public interface InterestService {
	void InterestInsert(Interest interest);
	void InterestDelete(Interest interest);
	Interest getInterest(Interest interest);
	List<House> interestSearchAll(String userinfo_num);
}
