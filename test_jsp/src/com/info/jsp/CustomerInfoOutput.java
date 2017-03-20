package com.info.jsp;

import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomerInfoOutput implements CustomerInfoService {
	public void CustomerInfoExecute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		CustomerInfoVO vo = new CustomerInfoVO();
		
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		int age = Integer.parseInt(request.getParameter("age"));
		response.setContentType("text/html; charset=utf-8");
		vo.setName(name);
		vo.setGender(gender);
		vo.setAge(age);
		
		CustomerInfoDAO dao = new CustomerInfoDAOImpl();
		dao.insert(vo);
		
		PersonalInfo person = new PersonalInfo();
		person.setName(name);
		person.setGender(gender);
		person.setAge(age);
		request.setAttribute("person", person);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/CustomerInfoOutput.jsp");
		dispatcher.forward(request, response);
		
	}
}
