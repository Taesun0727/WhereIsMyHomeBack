package com.mvc.service;

import java.util.List;

import com.mvc.vo.Interest;

public interface InterestService {
	void InterestInsert(Interest interest);
	void InterestDelete(int interest_num);
	List<Interest> InterestSearch(String userinfo_id);
}
