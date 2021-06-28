package com.kjh.exam.app.service;

import com.kjh.exam.app.dto.Member;
import com.kjh.exam.app.repository.MemberRepository;

public class MemberService {
	private MemberRepository memberRepository;
	
	public MemberService(){
		memberRepository = new MemberRepository();
	}
	

	public Member getMemberByLoginId(String loginId) {
			return memberRepository.getMemberByLoginId(loginId);
	}


	public void join(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNo, email);
		
	}


	

}
