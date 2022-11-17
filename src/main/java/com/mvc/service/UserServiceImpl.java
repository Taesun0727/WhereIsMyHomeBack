package com.mvc.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.mapper.UserMapper;
import com.mvc.vo.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;

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
		if(userinfo.getUserinfo_id() == null || userinfo.getUserinfo_password() == null)
			return null;
			
		return mapper.UserLogin(userinfo);
	}

	@Override
	public void UserLogout() {
		mapper.UserLogout();
	}


	@Override
	public void saveRefreshToken(String userid, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", refreshToken);
		mapper.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userid) throws Exception {
		return mapper.getRefreshToken(userid);
	}

	@Override
	public void deleteRefreshToken(String userid) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", null);
		mapper.deleteRefreshToken(map);
	}
}