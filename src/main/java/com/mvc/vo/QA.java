package com.mvc.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QA {
	private int qa_num;
	private int userinfo_num;
	private String qa_title;
	private String qa_content;
	private String qa_register_date;
	private String qa_update_date;
	private int qa_status;
	private int qa_question_num;
}
