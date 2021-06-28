package com.kjh.exam.app.interceptor;

import com.kjh.exam.app.Rq;

public class NeedLogoutInterceptor extends Interceptor{

	@Override
	public boolean run(Rq rq) {
		if(!rq.isLogined()) {
			return true;
		}
		switch (rq.getActionPath()) {
		case "/usr/membner/join":
		case "/usr/membner/login":
		case "/usr/membner/findLoginId":
		case "/usr/membner/findLoginPw":
			System.out.println("이미 로그인되었습니다.");
			return false;
		}
		return true;
	}

}
