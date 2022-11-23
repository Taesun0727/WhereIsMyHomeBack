package com.mvc.service;

import java.sql.SQLException;
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
	
	@Autowired
	private JwtService service;

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
	public Map<String, String> UserLogin(User userinfo) throws Exception {
		if(userinfo.getUserinfo_password() == null) {
			//카카오 로그인
			if(mapper.UserInfo(userinfo.getUserinfo_id()) == null) {
				mapper.UserJoin(userinfo);
			}
			
			User kuser = mapper.UserInfo(userinfo.getUserinfo_id());
//			System.out.println(userinfo.getUserinfo_id());
//			System.out.println(kuser);
			
//			if (kuser == null) {
//				mapper.UserJoin(userinfo);
//			}
			
			
			 String accessToken = service.createAccessToken("userinfo", kuser.getUserinfo_id());
	         String refreshToken = service.createRefreshToken("userinfo", kuser.getUserinfo_id());
	         
	         Map<String, Object> params = new HashMap<String, Object>() {{
	                put("userinfo", kuser.getUserinfo_id());
//	                put("level", 0);
	                put("token", refreshToken);
	            }};

	            mapper.saveRefreshToken(params);
	            
	            return new HashMap<String, String>() {{
	                put("access-token", accessToken);
//	                put("level", "0");
	                put("msg", "success");
	                put("refresh-token", refreshToken);
	            }};
	         
		}
		else {
			//일반 로그인
			User suser = mapper.UserLogin(userinfo);
            if (suser != null) {
                userinfo.setUserinfo_id(userinfo.getUserinfo_id());
                String accessToken = service.createAccessToken("uid", userinfo.getUserinfo_id());
                String refreshToken = service.createRefreshToken("uid", userinfo.getUserinfo_id());
                Map<String, Object> params = new HashMap<String, Object>() {{
                    put("uid", userinfo.getUserinfo_id());
//                    put("level", suser.getLevel());
                    put("token", refreshToken);
                }};

                mapper.saveRefreshToken(params);
                return new HashMap<String, String>() {{
                    put("access-token", accessToken);
//                    put("level", suser.getLevel() + "");
                    put("refresh-token", refreshToken);
                }};
            }
		}
			
		return null;
	}

	@Override
	public void UserLogout() {
		mapper.UserLogout();
	}


	@Override
	public void saveRefreshToken(String userid, String refreshToken) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
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

	@Override
	public User UserInfo(String userinfo_id) throws Exception {
		return mapper.UserInfo(userinfo_id);
	}

	@Override
	public void UserPwModify(User userinfo) {
		mapper.UserPwModify(userinfo);
	}
}