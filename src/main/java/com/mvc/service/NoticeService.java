package com.mvc.service;

import java.util.ArrayList;

import com.mvc.vo.Notice;

public interface NoticeService {
	ArrayList<Notice> noticeSearchAll();
	ArrayList<Notice> noticeSearchByTitle(String title);
	Notice noticeRead(String num);
	boolean noticeInsert(Notice n);
	boolean noticeUpdate(Notice n);
	boolean noticeDelete(String num);
}
