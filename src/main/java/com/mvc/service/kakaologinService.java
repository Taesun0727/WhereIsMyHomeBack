package com.mvc.service;

import java.util.List;

public interface kakaologinService {
	String getAccessToken(String auth_code);
	void getUserInfo(String access_Token);
}
