package com.info.jsp;

import javax.servlet.http.HttpServletRequest;

public class CustomerInfoFactory {
	
	public String uri;
	CustomerInfoService service;
	
	public CustomerInfoFactory(HttpServletRequest request) {
		this.uri = request.getRequestURI().split("/")[2];
	}
	
	public CustomerInfoService serviceImpl() throws Exception {
		
		switch(uri) {
		case "input" :	// 정보 입력창
			service = new CustomerInfoInput();
			break;
		case "output" :	// 정보 출력창
			service = new CustomerInfoOutput();
			break;
		case "delete" :
			service = new CustomerInfoDelete();
			break;
		case "updateselec" :
			service = new CustomerInfoUpdateSelec();
			break;
		case "update" :
			service = new CustomerInfoUpdate();
			break;
		default :
			throw new Exception("unsupported");
			
		}
		return service;
		
	}
}
