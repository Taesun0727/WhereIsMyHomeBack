package com.mvc.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.mapper.QAMapper;
import com.mvc.vo.QA;


@Service
public class QAServiceImpl implements QAService{
	
	@Autowired
	QAMapper mapper;

	@Override
	public ArrayList<QA> QASearchAll() {
		return mapper.QASearchAll();
	}
	
	@Override
	public ArrayList<QA> QASearch(int num) {
		// TODO Auto-generated method stub
		return mapper.QASearch(num);
	}

//	@Override
//	public ArrayList<QA> noticeSearchByTitle(String title) {
//		return mapper.noticeSearchByTitle(title);
//	}

	@Override
	public QA QARead(int num) {
		return mapper.QARead(num);
	}

	@Override
	public boolean QAInsert(QA q) {
		return mapper.QAInsert(q);
	}

	@Override
	public boolean QAUpdate(QA q) {
		return mapper.QAUpdate(q);
	}

	@Override
	public boolean QADelete(int num) {
		return mapper.QADelete(num);
	}

	@Override
	public QA QaAnswerRead(int num) {
		return mapper.QaAnswerRead(num);
	}

	@Override
	public boolean QAStateChange(int num) {
		return mapper.QAStateChange(num);
	}

	@Override
	public boolean AnswerInsert(QA q) {
		return mapper.AnswerInsert(q);
	}



}
