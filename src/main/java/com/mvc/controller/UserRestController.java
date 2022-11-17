package com.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.service.JwtServiceImpl;
import com.mvc.service.UserService;
import com.mvc.vo.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin("*")
@Api(value="UserRestController-Version 1")
public class UserRestController {
	
	public static final Logger logger = LoggerFactory.getLogger(UserRestController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private UserService service;	//interface type
	
	@PostMapping(value="/user/join")
	@ApiOperation(value = "회원가입", notes = "회원을 등록합니다.")
	public void join(@RequestBody User userinfo) {
		service.UserJoin(userinfo);
	}
	
	@PutMapping(value="/user/update")
	@ApiOperation(value = "회원수정", notes = "회원을 수정합니다.")
	public void modify(@RequestBody User userinfo) {
		service.UserModify(userinfo);
	}
	
	@DeleteMapping(value="/user/delete/{userinfo_num}")
	@ApiOperation(value = "회원삭제", notes = "회원을 삭제합니다.")
	public void delete(@PathVariable int userinfo_num) {
		service.UserDelete(userinfo_num);
	}
	
	@PostMapping(value="/user/search/{userinfo_id}")
	@ApiOperation(value = "회원검색", notes = "ID로 회원을 검색합니다.")
	public User search(@PathVariable String userinfo_id) {
		return service.UserSearch(userinfo_id);
	}
	
	@PostMapping(value="/user/login")
	@ApiOperation(value = "로그인", notes = "로그인이 되었는지 확인합니다.")
	public ResponseEntity<Map<String, Object>> login(@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) User userinfo) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			User user = service.UserLogin(userinfo);
			if (user != null) {
				String accessToken = jwtService.createAccessToken("userinfo", user.getUserinfo_id());// key, data
				String refreshToken = jwtService.createRefreshToken("userinfo", user.getUserinfo_id());// key, data
				service.saveRefreshToken(userinfo.getUserinfo_id(), refreshToken);
				logger.debug("로그인 accessToken 정보 : {}", accessToken);
				logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
				
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userinfo_id}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userinfo_id") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userinfo_id,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				User user = service.UserInfo(userinfo_id);
				resultMap.put("userInfo", user);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@GetMapping(value="/user/logout")
	@ApiOperation(value = "로그아웃", notes = "로그아웃이 되었는지 확인합니다.")
	public void logout(HttpSession session) {
		session.invalidate();
	}
	
}