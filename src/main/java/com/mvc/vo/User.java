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
public class User {
	private int userinfo_num;
    private String userinfo_id;
    private String userinfo_password;
    private String userinfo_nick;
    private int userinfo_level;
    private String userinfo_token;
}