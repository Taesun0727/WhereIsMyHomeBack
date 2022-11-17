package com.mvc.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mvc.vo.User;

@Mapper
public interface UserMapper {
	void UserJoin(User userinfo);
	void UserModify(User userinfo);
	void UserDelete(int userinfo_num);
	User UserSearch(String userinfo_id);
	User UserLogin(User userinfo);
	void UserLogout();
	public void saveRefreshToken(Map<String, String> map) throws Exception;
	public Object getRefreshToken(String userid) throws Exception;
	public void deleteRefreshToken(Map<String, String> map) throws Exception;
}
