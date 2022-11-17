package com.mvc.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.mvc.vo.QA;

@Mapper
public interface QAMapper {
	ArrayList<QA> QASearchAll();
//	ArrayList<QA> noticeSearchByTitle(String title);
	QA QARead(int num);
	boolean QAInsert(QA q);
	boolean QAUpdate(QA q);
	boolean QADelete(int num);
	QA QaAnswerRead(int num);
	boolean QAStateChange(int num);
}
