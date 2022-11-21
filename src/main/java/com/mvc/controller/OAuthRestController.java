package com.mvc.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.service.kakaologinServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "operation")
public class OAuthRestController {
	
	@Autowired
	kakaologinServiceImpl service;
	
	@GetMapping("/kakaologin")
	@ApiOperation(value= "회원인증", notes="회원인증입니다.")
	public HashMap<String, String> klogin(@RequestParam(value= "code") String authorize_code){
		String access_token = service.getAccessToken(authorize_code);
		HashMap<String, String> userinfo = service.getUserInfo(access_token);
        return userinfo;
	}
	
}