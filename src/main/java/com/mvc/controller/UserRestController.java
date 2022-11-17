package com.mvc.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.service.UserService;
import com.mvc.vo.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@Api(value="UserRestController-Version 1")
public class UserRestController {
	
	@Autowired
	UserService service;	//interface type
	
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
	public String login(HttpSession session, @RequestBody User userinfo) {
		User user = service.UserLogin(userinfo);
		if(user == null) {
			return "로그인 실패";
		}
		else {
			String ID = user.getUserinfo_id();
			String PW = user.getUserinfo_password();
			HashMap<String, String> map = new HashMap<>();
		
			map.put("id", ID);
			map.put("pw", PW);
			session.setAttribute("USER", map);
			return "로그인 성공";
		}
	}
	
	@GetMapping(value="/user/logout")
	@ApiOperation(value = "로그아웃", notes = "로그아웃이 되었는지 확인합니다.")
	public void logout(HttpSession session) {
		session.invalidate();
	}
	
}