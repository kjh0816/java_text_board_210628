package com.kjh.exam.app;

import java.util.HashMap;
import java.util.Map;

import com.kjh.exam.app.container.Container;
import com.kjh.exam.app.dto.Member;

import lombok.Getter;

public class Rq {
	private String command;
	private Map<String, String> params;
	@Getter
	private String controllerTypeCode;
	@Getter
	private String controllerName;
	private String actionMethodName;
	private String queryString = "";
	@Getter
	private boolean isValid = true;
	
	
	public void setCommand(String command) {
		this.command = command;
		
		params = new HashMap<String, String>();
		
		String[] commandBits = command.split("\\?", 2);
		
		if(commandBits.length == 2) {
			queryString = commandBits[1].trim();
			
			String[] queryStringBits = queryString.split("&");
			
			for(String queryStringBit : queryStringBits) {
				
				String[] queryStringBitBits = queryStringBit.split("=", 2);
				String paramName = queryStringBitBits[0];
				String paramValue = queryStringBitBits[1];
				
				params.put(paramName, paramValue);
			}

		}
		
		commandBits = commandBits[0].split("/", 4);
		
		if(commandBits.length != 4) {
			isValid = false;
			return;
		}
		
		controllerTypeCode = commandBits[1].trim();
		controllerName = commandBits[2].trim();
		actionMethodName = commandBits[3].trim();
		
	}
	
	public String getActionPath() {
		return "/" + controllerTypeCode + "/" + controllerName + "/" + actionMethodName;
	}
	
	public int getIntParam(String paramName, int defaultValue) {
		if(!params.containsKey(paramName)) {
			return defaultValue;
		}
		
		try {
			return Integer.parseInt(params.get(paramName));	
		}catch(NumberFormatException e) {
			return defaultValue;
		}
	}
	
	public void setSessionAttr(String key, Object value) {
		Session session = Container.getSession();
		session.setAttribute(key, value);
	}
	
	public Object getSessionAttr(String key) {
		Session session = Container.getSession();
		return session.getAttribute(key);
	}
	
	public void removeSessionAttr(String key) {
		Session session = Container.getSession();
		session.removeAttribute(key);
	}
	
	public boolean hasSessionAttr(String key) {
		Session session = Container.getSession();
		return session.hasAttribute(key);
	}
	
	public Member getLoginedMember() {
		return (Member) getSessionAttr("loginedMember");
	}
	
	public boolean isLogined() {
		return hasSessionAttr("loginedMember");
	}
	
	public void login(Member member) {
		setSessionAttr("loginedMember", member);
	}
	
	public void logout() {
		removeSessionAttr("loginedMember");
	}
	
	
	
}
