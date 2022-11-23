package com.mvc.service;

import java.util.HashMap;

public interface kakaologinService {
//	String getAccessToken(String auth_code);
	HashMap<String, String> getUserInfo(String access_Token);
}
