package com.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.service.InterestService;
import com.mvc.vo.Interest;

import io.swagger.annotations.Api;

@RestController
@CrossOrigin("*")
@Api(value="InterestRestController-Version 1")
public class InterestRestController {
	
	@Autowired
	InterestService service;
	
	@PostMapping(value="/interest/insert")
	public void insert(@ModelAttribute Interest interest) {
		service.InterestInsert(interest);
	}
	
	@DeleteMapping(value="/interest/delete/{interest_num}")
	public void delete(@PathVariable int interest_num) {
		service.InterestDelete(interest_num);
	}
}
