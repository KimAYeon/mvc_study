package com.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class RoleInterceptor extends HandlerInterceptorAdapter {
	
	private static final String LOGIN = "login";
	private static final String DEST = "destination";
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	private void saveDest(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String query = request.getQueryString();
		if(query == null || query.equals("null")) {
			query="";
		} else {
			query= "?" + query;
		}
		
		if(request.getMethod().equals("GET")) {
			logger.info("dest: " + (uri + query));
			request.getSession().setAttribute(DEST, uri+query);
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		
		if(session.getAttribute(LOGIN) == null) {
			logger.info("current user is not logined");
			saveDest(request);
			response.sendRedirect("/user/login");
			return false;
		}
		return true;
	}
	
}
