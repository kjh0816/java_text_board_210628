package com.kjh.exam.app.repository;

import java.util.ArrayList;
import java.util.List;

import com.kjh.exam.app.dto.Member;
import com.kjh.exam.util.Util;

public class MemberRepository {

	private List<Member> members;
	private int memberLastId;

	public MemberRepository() {
		members = new ArrayList<Member>();
		memberLastId = 0;
	}

	public Member getMemberByLoginId(String loginId) {
		for (Member member : members) {
			if (member.loginId == loginId) {
				return member;
			}
		}
		return null;
	}

	public void join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		Member member = new Member();
		++memberLastId;
		member.id = memberLastId;
		member.regDate = Util.getNowDateStr();
		member.updateDate = Util.getNowDateStr();
		member.loginId = loginId;
		member.loginPw = loginPw;
		member.name = name;
		member.nickname = nickname;
		member.cellphoneNo = cellphoneNo;
		member.email = email;
		
		members.add(member);
	}



}
