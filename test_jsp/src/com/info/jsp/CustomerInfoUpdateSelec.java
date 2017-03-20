package com.info.jsp;

import java.io.DataInputStream;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomerInfoUpdateSelec implements CustomerInfoService {

	@Override
	public void CustomerInfoExecute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		CustomerInfoDAO dao = new CustomerInfoDAOImpl();
		int customNo = Integer.parseInt(request.getParameter("customNo"));
		request.setAttribute("person", dao.select(customNo));
		System.out.println(customNo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/CustomerInfoUpdate.jsp");
		dispatcher.forward(request, response);
	}

}
 