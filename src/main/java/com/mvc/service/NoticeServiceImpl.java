package com.mvc.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvc.mapper.NoticeMapper;
import com.mvc.vo.Notice;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	NoticeMapper mapper;

	@Override
	public ArrayList<Notice> noticeSearchAll() {
		return mapper.noticeSearchAll();
	}

	@Override
	public ArrayList<Notice> noticeSearchByTitle(String title) {
		return mapper.noticeSearchByTitle(title);
	}

	@Override
	public Notice noticeRead(String num) {
		return mapper.noticeRead(num);
	}

	@Override
	public boolean noticeInsert(Notice n) {
		return mapper.noticeInsert(n);
	}

	@Override
	public boolean noticeUpdate(Notice n) {
		return mapper.noticeUpdate(n);
	}

	@Override
	public boolean noticeDelete(String num) {
		return mapper.noticeDelete(num);
	}



}
