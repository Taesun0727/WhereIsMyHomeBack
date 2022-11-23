package com.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.message.AuthException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.service.UserService;
import com.mvc.service.kakaologinService;
import com.mvc.vo.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@Api(value = "operation")
public class OAuthRestController {
	
	@Autowired
	kakaologinService kakaologinService;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/kakaologin")
	@ApiOperation(value= "회원인증", notes="회원인증입니다.")
	public ResponseEntity<?> klogin(@RequestParam(value="code") String access_token) throws Exception{
//      String access_token = oauthService.getKakaoAccessToken(authorize_code);
      HashMap<String, String> userinfo = kakaologinService.getUserInfo(access_token);
      
      
      if (userinfo == null)
          throw new AuthException("옳바르지 않은 토큰값입니다.");
      if (userinfo.size() < 3)
          throw new AuthException("닉네임과 이메일을 필수 동의 해야합니다.");

      User user = new User();
      user.setUserinfo_id(userinfo.get("email").toString());
      user.setUserinfo_nick(userinfo.get("nickname").toString());
//      user.setUserinfo_token(userinfo.get("access-token").toString());
      Map<String, String> result = userService.UserLogin(user);

      return new ResponseEntity<Object>(result, HttpStatus.OK);
	}
	
}