package com.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.mapper.UserMapper;
import com.mvc.vo.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper mapper;

	@Override
	public void UserJoin(User userinfo) {		
		mapper.UserJoin(userinfo);
	}

	@Override
	public void UserModify(User userinfo) {
		mapper.UserModify(userinfo);
	}

	@Override
	public void UserDelete(int userinfo_num) {
		mapper.UserDelete(userinfo_num);
	}

	@Override
	public User UserSearch(String userinfo_id) {
		return mapper.UserSearch(userinfo_id);
	}

	@Override
	public User UserLogin(User userinfo) {
		return mapper.UserLogin(userinfo);
	}

	@Override
	public void UserLogout() {
		mapper.UserLogout();
	}
}