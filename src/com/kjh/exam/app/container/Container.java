package com.kjh.exam.app.container;

import java.util.Scanner;

import com.kjh.exam.app.Session;
import com.kjh.exam.app.controller.UsrArticleController;
import com.kjh.exam.app.controller.UsrMemberController;
import com.kjh.exam.app.controller.UsrSystemController;
import com.kjh.exam.app.interceptor.NeedLoginInterceptor;
import com.kjh.exam.app.interceptor.NeedLogoutInterceptor;
import com.kjh.exam.app.repository.ArticleRepository;
import com.kjh.exam.app.repository.MemberRepository;
import com.kjh.exam.app.service.ArticleService;
import com.kjh.exam.app.service.MemberService;

import lombok.Getter;

public class Container {
	@Getter
	private static Scanner sc;
	@Getter
	private static Session session;
	
	@Getter
	private static MemberService memberService;
	@Getter
	private static ArticleService articleService;
	
	@Getter
	private static MemberRepository memberRepository;
	@Getter
	private static ArticleRepository articleRepository;
	
	
	
	@Getter
	private static NeedLoginInterceptor needLoginInterceptor;
	@Getter
	private static NeedLogoutInterceptor needLogoutInterceptor;
	
	@Getter
	private static UsrSystemController usrSystemController;
	@Getter
	private static UsrArticleController usrArticleController;
	@Getter
	private static UsrMemberController usrMemberController;
	
	static {
		sc = new Scanner(System.in);
		session = new Session();
		
		memberService = new MemberService();
		articleService = new ArticleService();
		
		articleRepository = new ArticleRepository();
		memberRepository = new MemberRepository();
		
		needLoginInterceptor = new NeedLoginInterceptor();
		needLogoutInterceptor = new NeedLogoutInterceptor();
		
		
		usrSystemController = new UsrSystemController();
		usrArticleController = new UsrArticleController();
		usrMemberController = new UsrMemberController();
	}

	
}
