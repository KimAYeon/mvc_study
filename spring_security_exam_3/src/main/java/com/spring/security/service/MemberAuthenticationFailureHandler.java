package com.spring.security.service;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class MemberAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	private String defaultFailureUrl;
	
	public MemberAuthenticationFailureHandler() {
		super();
	}

	public MemberAuthenticationFailureHandler(String defaultFailureUrl) {
		super(defaultFailureUrl);
		this.defaultFailureUrl = defaultFailureUrl;
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String msg = URLEncoder.encode(exception.getMessage(), "UTF-8");
		setDefaultFailureUrl(getFailureUrl(msg));
		super.onAuthenticationFailure(request, response, exception);
		System.out.println("exception : " + exception.getMessage());
	}

	private String getFailureUrl(String error) {
		return defaultFailureUrl + "=" + error;
	}
}
