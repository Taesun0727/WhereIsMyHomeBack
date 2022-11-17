package com.mvc.service;

import com.mvc.vo.User;

public interface UserService {
	
	void UserJoin(User userinfo);
	void UserModify(User userinfo);
	void UserDelete(int userinfo_num);
	User UserSearch(String userinfo_id);
	User UserLogin(User userinfo);
	void UserLogout();
}