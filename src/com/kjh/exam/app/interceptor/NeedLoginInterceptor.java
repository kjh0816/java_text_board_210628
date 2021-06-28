package com.kjh.exam.app.interceptor;

import com.kjh.exam.app.Rq;

public class NeedLoginInterceptor extends Interceptor{

	@Override
	public boolean run(Rq rq) {
		if(rq.isLogined()) {
			return true;
		}
//		위의 필터에 의해서 로그인이 안 된 경우에만 아래가 실행된다.
//		즉, 로그인이 안 됐는데, action path가 아래와 같으면 false가 return 된다.
		switch(rq.getActionPath()) {
		case "/usr/article/write":
		case "/usr/article/modify":
		case "/usr/article/delete":
			System.out.println("로그인 후 이용해주세요.");
			return false;
		}
		
		return true;
		
	}

}
