package com.kjh.exam.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.kjh.exam.app.container.Container;
import com.kjh.exam.app.controller.Controller;
import com.kjh.exam.app.dto.Member;
import com.kjh.exam.app.interceptor.Interceptor;

public class App {
	
	Scanner sc;
	
	
	App(){
		sc = new Scanner(System.in);
	}
	

	public void run() {
		
		
		while(true) {
			
			String prompt = "명령어";
			Rq rq = new Rq();
			if(rq.isLogined()) {
				Member loginedMember = rq.getLoginedMember();
				prompt = loginedMember.getNickname();
			}
			
			System.out.printf("%s", prompt);
			String command = sc.nextLine().trim();
			rq.setCommand(command);
			
			if(!rq.isValid()) {
				System.out.println("잘못된 명령어입니다.");
				continue;
			}
			
			if(!runInterceptors(rq)) {
				continue;
			}
			
			Controller controller = getControllerByRequestUri(rq);
			controller.performAction(rq);
			
			
			if(rq.getActionPath().equals("/usr/system/exit")) {
				break;
			}
			
		}

		
	}


	private boolean runInterceptors(Rq rq) {
		List<Interceptor> interceptors = new ArrayList<Interceptor>();
		
		interceptors.add(Container.getNeedLoginInterceptor());
		interceptors.add(Container.getNeedLogoutInterceptor());
		
		for(Interceptor interceptor : interceptors) {
				if(!interceptor.run(rq)) {
					return false;
				}
		}
		
		return true;
	}
	
	private Controller getControllerByRequestUri(Rq rq) {
		switch (rq.getControllerTypeCode()) {
		case "usr":
			switch (rq.getControllerName()) {
			case "article":
				return Container.getUsrArticleController();
			case "member":
				return Container.getUsrMemberController();
			case "system":
				return Container.getUsrSystemController();
			}

			break;
		}

		return null;
	}

}
