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
public class Notice {
	private String notice_num;
	private String userinfo_nick;
	private String notice_title;
	private String notice_content;
	private String notice_register_date;
	private String notice_update_date;
	private String notice_hit;
	
}
