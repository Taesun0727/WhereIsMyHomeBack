package com.mvc.service;

import java.util.ArrayList;

import com.mvc.vo.QA;


public interface QAService {
	ArrayList<QA> QASearchAll();
	ArrayList<QA> QASearch(int num);
//	ArrayList<QA> noticeSearchByTitle(String title);
	QA QARead(int num);
	boolean QAInsert(QA q);
	boolean QAUpdate(QA q);
	boolean QADelete(int num);
	QA QaAnswerRead(int num);
	boolean QAStateChange(int num);
	boolean AnswerInsert(QA q);
}
