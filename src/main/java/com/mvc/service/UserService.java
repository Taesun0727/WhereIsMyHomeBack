package com.mvc.service;

import java.util.Map;

import com.mvc.vo.User;

public interface UserService {
	
	void UserJoin(User userinfo);
	void UserModify(User userinfo);
	void UserDelete(int userinfo_num);
	User UserSearch(String userinfo_id);
	public Map<String, String> UserLogin(User userinfo) throws Exception;
	void UserLogout();
	public void saveRefreshToken(String userid, String refreshToken) throws Exception;
	public Object getRefreshToken(String userid) throws Exception;
	public void deleteRefreshToken(String userid) throws Exception;
	public User UserInfo(String userinfo_id) throws Exception;
	void UserPwModify(User userinfo);
}