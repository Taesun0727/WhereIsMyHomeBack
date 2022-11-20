package com.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.service.HouseService;
import com.mvc.vo.House;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@Api(value = "아파트 정보 API 입니다.")
public class HouseRestController {

	@Autowired
	HouseService service;
	
	@ApiOperation(value = "시 검색", notes = "시 <big>전체 목록</big>을 반환해 줍니다.")
	@GetMapping(value="/si")
	public ResponseEntity<?> siSearch() {
		List<String> si_list = service.siSearch();
		if (si_list != null && !si_list.isEmpty()) {
			ResponseEntity<List<String>> response = new ResponseEntity<List<String>>(si_list, HttpStatus.OK);
			return response;
		} else {
			return extracted();
		}
	}
	
	@ApiOperation(value = "구군 검색", notes = "구군 <big>전체 목록</big>을 반환해 줍니다.")
	@GetMapping(value="/gugun/{gugun}")
	public ResponseEntity<?> gugunSearch(@PathVariable String gugun) {
		List<String> gugun_list = service.gugunSearch(gugun);
		if (gugun_list != null && !gugun_list.isEmpty()) {
			ResponseEntity<List<String>> response = new ResponseEntity<List<String>>(gugun_list, HttpStatus.OK);
			return response;
		} else {
			return extracted();
		}
	}
	
	@ApiOperation(value = "동 검색", notes = "동 <big>전체 목록</big>을 반환해 줍니다.")
	@GetMapping(value="/dong/{dong}")
	public ResponseEntity<?> dongSearch(@PathVariable String dong) {
		List<String> dong_list = service.dongSearch(dong);
		if (dong_list != null && !dong_list.isEmpty()) {
			System.out.println(dong_list);
			ResponseEntity<List<String>> response = new ResponseEntity<List<String>>(dong_list, HttpStatus.OK);
			return response;
		} else {
			return extracted();
		}
	}
	
	@ApiOperation(value = "동별 아파트 검색", notes = "동별 아파트 <big>전체 목록</big>을 반환해 줍니다.")
	@GetMapping(value="/dong/select/{dong}")
	public ResponseEntity<?> dongSelect(@PathVariable String dong) {
		List<House> house_list = service.dongSelect(dong);
		if (house_list != null && !house_list.isEmpty()) {
			ResponseEntity<List<House>> response = new ResponseEntity<List<House>>(house_list, HttpStatus.OK);
			return response;
		} else {
			return extracted();
		}
	}
	
	@ApiOperation(value = "아파트 검색", notes = "아파트 <big>전체 목록</big>을 반환해 줍니다.")
	@GetMapping(value="/house/{house}")
	public ResponseEntity<?> houseSearch(@PathVariable String house) {
		List<House> house_list = service.houseSearch(house);
		if (house_list != null && !house_list.isEmpty()) {
			ResponseEntity<List<House>> response = new ResponseEntity<List<House>>(house_list, HttpStatus.OK);
			return response;
		} else {
			return extracted();
		}
	}
	
	@ApiOperation(value = "아파트 검색", notes = "아파트 <big>전체 목록</big>을 반환해 줍니다.")
	@GetMapping(value="/house/code/{aptCode}")
	public ResponseEntity<?> getHouse(@PathVariable String aptCode) {
		House house = service.getHouse(aptCode);
		if (house != null) {
			ResponseEntity<House> response = new ResponseEntity<House>(house, HttpStatus.OK);
			return response;
		} else {
			return extracted();
		}
	}
	
	@ApiOperation(value = "아파트 거래 검색", notes = "아파트 <big>거래 전체 목록</big>을 반환해 줍니다.")
	@GetMapping(value="/house/deal/{aptCode}")
	public ResponseEntity<?> getDeal(@PathVariable String aptCode) {
		List<House> deal_list = service.getDeal(aptCode);
		if (deal_list != null && !deal_list.isEmpty()) {
			ResponseEntity<List<House>> response = new ResponseEntity<List<House>>(deal_list, HttpStatus.OK);
			return response;
		} else {
			return extracted();
		}
	}
	
	private ResponseEntity extracted() {
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	
}
