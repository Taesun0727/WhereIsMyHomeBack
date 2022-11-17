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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mvc.service.NoticeService;
import com.mvc.vo.Notice;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@Api(value = "공지사항 정보 API 입니다.")
public class NoticeRestController {

	@Autowired
	NoticeService service;
	
	@ApiOperation(value = "공지사항 전체 검색", notes = "공지사항 <big>전체 목록</big>을 반환해 줍니다.")
	@GetMapping(value="/notice")
	public ResponseEntity<?> noticeSearchAll() {
		List<Notice> notice_list = service.noticeSearchAll();
		if (notice_list != null && !notice_list.isEmpty()) {
			ResponseEntity<List<Notice>> response = new ResponseEntity<List<Notice>>(notice_list, HttpStatus.OK);
			return response;
		} else {
			return extracted();
		}
	}
	
	@ApiOperation(value = "공지사항 제목 검색", notes = "공지사항 <big>제목 검색 목록</big>을 반환해 줍니다.")
	@GetMapping(value="/notice/title/{title}")
	public ResponseEntity<?> noticeSearchByTitle(@PathVariable String title) {
		List<Notice> notice_list = service.noticeSearchByTitle(title);
		if (notice_list != null && !notice_list.isEmpty()) {
			ResponseEntity<List<Notice>> response = new ResponseEntity<List<Notice>>(notice_list, HttpStatus.OK);
			return response;
		} else {
			return extracted();
		}
	}
	
	@ApiOperation(value = "공지사항 읽기", notes = "공지사항 <big>num으로 찾은 값</big>을 반환해 줍니다.")
	@GetMapping(value="/notice/num/{num}")
	public Notice noticeRead(@PathVariable String num) {
		return service.noticeRead(num);
	}
	
	@ApiOperation(value = "공지사항 추가", notes = "공지사항 추가합니다.")
	@PostMapping(value="/notice")
	public String noticeInsert(@RequestBody Notice n) {
		if(service.noticeInsert(n)) {
			return "추가성공";
		} else {
			return "추가실패";
		}
	}
	
	@ApiOperation(value = "공지사항 수정", notes = "공지사항 수정합니다.")
	@PutMapping(value="/notice")
	public String noticeUpdate(@RequestBody Notice n) {
		if(service.noticeUpdate(n)) {
			return "수정성공";
		} else {
			return "수정실패";
		}
	}
	
	@ApiOperation(value = "공지사항 삭제", notes = "공지사항 삭제합니다.")
	@DeleteMapping(value="/notice/{num}")
	public String noticeDelete(@PathVariable String num) {
		if(service.noticeDelete(num)) {
			return "삭제성공";
		} else {
			return "삭제실패";
		}
	}
	
	private ResponseEntity extracted() {
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}
