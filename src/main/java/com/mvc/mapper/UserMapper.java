package com.mvc.mapper;

import java.sql.SQLException;
import java.util.List;
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
	User UserInfo(String userinfo_id) throws SQLException;
	void UserLogout();
	public void saveRefreshToken(Map<String, String> map) throws Exception;
	public Object getRefreshToken(String userid) throws Exception;
	public void deleteRefreshToken(Map<String, String> map) throws Exception;
	void UserPwModify(User userinfo);
	void KakaoUserInfo(User userInfo);
}
