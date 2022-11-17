package com.mvc.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.mvc.vo.Notice;

@Mapper
public interface NoticeMapper {
	ArrayList<Notice> noticeSearchAll();
	ArrayList<Notice> noticeSearchByTitle(String title);
	Notice noticeRead(String num);
	boolean noticeInsert(Notice n);
	boolean noticeUpdate(Notice n);
	boolean noticeDelete(String num);
}
