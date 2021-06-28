package com.kjh.exam.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Member {
	public int id;
	public String regDate;
	public String updateDate;
	public String loginId;
	public String loginPw;
	public String name;
	public String nickname;
	public String cellphoneNo;
	public String email;

}
