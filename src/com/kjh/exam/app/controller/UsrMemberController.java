package com.kjh.exam.app.controller;

import java.util.Scanner;

import com.kjh.exam.app.Rq;
import com.kjh.exam.app.container.Container;
import com.kjh.exam.app.dto.Member;
import com.kjh.exam.app.service.MemberService;
import com.kjh.exam.util.Util;

public class UsrMemberController extends Controller{
		private Scanner sc;
		private MemberService memberService;
		
		public UsrMemberController() {
			sc = Container.getSc();
			memberService = Container.getMemberService();
		}
		
		@Override
		public void performAction(Rq rq) {
			
			if(rq.getActionPath().equals("/usr/member/login")) {
					actionLogin(rq);
			}else if(rq.getActionPath().equals("/usr/member/logout")) {
					actionLogout(rq);
			}else if(rq.getActionPath().equals("/usr/member/join")) {
					actionJoin(rq);
			}
			
		}

		private void actionJoin(Rq rq) {
			System.out.println("로그인 아이디를 입력해주세요.");
			String loginId = sc.nextLine().trim();
			if(loginId.length() == 0) {
				System.out.println("로그인 아이디를 입력해주세요.");
				return;
			}
			Member member = memberService.getMemberByLoginId(loginId);
			if(member != null) {
				System.out.printf("%s는(은) 이미 존재하는 로그인 아이디입니다.\n", loginId);
				return;
			}
				
			System.out.println("로그인 비밀번호를 입력해주세요.");
			String loginPw = sc.nextLine().trim();
			
			System.out.println("로그인 비밀번호를 한번 더 입력해주세요.");
			String loginPwConfirm = sc.nextLine().trim();
			if(!loginPw.equals(loginPwConfirm)) {
				System.out.println("입력하신 두 비밀번호가 일치하지 않습니다.");
				return;
			}
			
			System.out.println("이름을 입력해주세요.");
			String name = sc.nextLine().trim();
			if(name.length() == 0) {
				System.out.println("이름을 입력해주세요.");
				return;
			}
			System.out.println("별명을 입력해주세요.");
			String nickname = sc.nextLine().trim();
			if(nickname.length() == 0) {
				System.out.println("별명을 입력해주세요.");
				return;
			}
			System.out.println("핸드폰 번호를 입력해주세요.");
			String cellphoneNo = sc.nextLine().trim();
			if(cellphoneNo.length() == 0) {
				System.out.println("핸드폰 번호를 입력해주세요.");
				return;
			}
			System.out.println("이메일을 입력해주세요.");
			String email = sc.nextLine().trim();
			if(email.length() == 0) {
				System.out.println("이메일을 입력해주세요.");
				return;
			}
			
			
			memberService.join(loginId, loginPw, name, nickname, cellphoneNo, email);
			System.out.printf("%s님의 회원가입이 완료되었습니다.", nickname);
			
			
		}

		private void actionLogout(Rq rq) {
				rq.logout();
		}

		private void actionLogin(Rq rq) {
			String loginId = sc.nextLine().trim();
			
			if(loginId.length() == 0) {
				System.out.println("로그인 아이디를 입력해주세요.");
				return;
			}
			
			Member member = memberService.getMemberByLoginId(loginId);
			if(member == null) {
				System.out.printf("%s는(은) 존재하지 않는 로그인 아이디입니다.\n", loginId);
				return;
			}
			
			
			String loginPw = sc.nextLine().trim();
			
			if(loginPw.length() == 0) {
				System.out.println("로그인 비밀번호를 입력해주세요.");
				return;
			}
			
			if(!member.getLoginPw().equals(loginPw)) {
				System.out.println("비밀번호가 일치하지 않습니다.");
				return;
			}
			
			
			rq.login(member);
			
			
			System.out.printf("%d님, 환영합니다.\n", member.getNickname());
			
			
			
			
		}
		
		
}
