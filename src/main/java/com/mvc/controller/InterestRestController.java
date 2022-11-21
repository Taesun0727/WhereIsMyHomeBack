package com.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.service.InterestService;
import com.mvc.vo.House;
import com.mvc.vo.Interest;
import com.mvc.vo.Notice;
import com.mvc.vo.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@Api(value="InterestRestController-Version 1")
public class InterestRestController {
	
	@Autowired
	InterestService service;
	
	@GetMapping(value="/interest/{userinfo_num}/{aptCode}")
	@ApiOperation(value = "관심검색", notes = "아파트코드와 유저넘버로 관심을 검색합니다.")
	public boolean search(@ModelAttribute Interest interest) {
		Interest info = service.getInterest(interest);
		if (info == null) {
			return false;
		}
		return true;
	}
	
	@ApiOperation(value = "관심지역 전체 검색", notes = "관심지역 <big>전체 목록</big>을 반환해 줍니다.")
	@GetMapping(value="/interest/{userinfo_num}")
	public ResponseEntity<?> noticeSearchAll(@PathVariable String userinfo_num) {
		List<House> interest_houses = service.interestSearchAll(userinfo_num);
		if (interest_houses != null && !interest_houses.isEmpty()) {
			ResponseEntity<List<House>> response = new ResponseEntity<List<House>>(interest_houses, HttpStatus.OK);
			return response;
		} else {
			return extracted();
		}
	}
	
	@PostMapping(value="/interest")
	public void insert(@RequestBody Interest interest) {
		System.out.println(interest);
		service.InterestInsert(interest);
	}
	
	@DeleteMapping(value="/interest/{userinfo_num}/{aptCode}")
	public void delete(@ModelAttribute Interest interest_num) {
		service.InterestDelete(interest_num);
	}
	
	private ResponseEntity extracted() {
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}
