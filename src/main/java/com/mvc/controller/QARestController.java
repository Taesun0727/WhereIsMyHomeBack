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

import com.mvc.service.QAService;
import com.mvc.vo.QA;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin("*")
@Api(value = "공지사항 정보 API 입니다.")
public class QARestController {

	@Autowired
	QAService service;
	
	@ApiOperation(value = "Q&A 전체 검색", notes = "Q&A <big>전체 목록</big>을 반환해 줍니다.")
	@GetMapping(value="/qna")
	public ResponseEntity<?> QASearchAll() {
		List<QA> notice_list = service.QASearchAll();
		if (notice_list != null && !notice_list.isEmpty()) {
			ResponseEntity<List<QA>> response = new ResponseEntity<List<QA>>(notice_list, HttpStatus.OK);
			return response;
		} else {
			return extracted();
		}
	}
	
	@ApiOperation(value = "Q&A 자신 검색", notes = "Q&A <big>자신 목록</big>을 반환해 줍니다.")
	@GetMapping(value="/qna/user/{num}")
	public ResponseEntity<?> QASearch(@PathVariable int num) {
		List<QA> notice_list = service.QASearch(num);
		if (notice_list != null && !notice_list.isEmpty()) {
			ResponseEntity<List<QA>> response = new ResponseEntity<List<QA>>(notice_list, HttpStatus.OK);
			return response;
		} else {
			return extracted();
		}
	}
	
//	@ApiOperation(value = "공지사항 제목 검색", notes = "공지사항 <big>제목 검색 목록</big>을 반환해 줍니다.")
//	@GetMapping(value="/notice/title/{title}")
//	public ResponseEntity<?> noticeSearchByTitle(@PathVariable String title) {
//		List<Notice> notice_list = service.noticeSearchByTitle(title);
//		if (notice_list != null && !notice_list.isEmpty()) {
//			ResponseEntity<List<Notice>> response = new ResponseEntity<List<Notice>>(notice_list, HttpStatus.OK);
//			return response;
//		} else {
//			return extracted();
//		}
//	}
	
	@ApiOperation(value = "Q&A 읽기", notes = "Q&A <big>num으로 찾은 값</big>을 반환해 줍니다.")
	@GetMapping(value="/qna/{num}")
	public QA QARead(@PathVariable int num) {
		return service.QARead(num);
	}
	
	@ApiOperation(value = "Q&A 추가", notes = "Q&A 추가합니다.")
	@PostMapping(value="/qna")
	public String QAInsert(@RequestBody QA q) {
		if(service.QAInsert(q)) {
			return "추가성공";
		} else {
			return "추가실패";
		}
	}
	
	@ApiOperation(value = "answer 추가", notes = "answer 추가합니다.")
	@PostMapping(value="/answer")
	public String AnswerInsert(@RequestBody QA q) {
		int num = q.getQa_question_num();
		service.QAStateChange(num);
		if(service.AnswerInsert(q)) {
			return "추가성공";
		} else {
			return "추가실패";
		}
	}
	
	@ApiOperation(value = "Q&A 수정", notes = "Q&A 수정합니다.")
	@PutMapping(value="/qna")
	public String QAUpdate(@RequestBody QA q) {
		if(service.QAUpdate(q)) {
			return "수정성공";
		} else {
			return "수정실패";
		}
	}
	
	@ApiOperation(value = "Q&A 삭제", notes = "Q&A 삭제합니다.")
	@DeleteMapping(value="/qna/{num}")
	public String QADelete(@PathVariable int num) {
		if(service.QADelete(num)) {
			return "삭제성공";
		} else {
			return "삭제실패";
		}
	}
	
	@ApiOperation(value = "Q&A 답변읽기", notes = "Q&A답변 <big>num으로 찾은 값</big>을 반환해 줍니다.")
	@GetMapping(value="/qna/answer/{num}")
	public QA QaAnswerRead(@PathVariable int num) {
		return service.QaAnswerRead(num);
	}
	
	@ApiOperation(value = "Q&A 답변여부", notes = "Q&A 답변여부 판별합니다.")
	@PutMapping(value="/qna/complete/{num}")
	public String QAStateChange(@PathVariable int num) {
		if(service.QAStateChange(num)) {
			return "답변완료";
		} else {
			return "답변없음";
		}
	}
	
	private ResponseEntity extracted() {
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}
