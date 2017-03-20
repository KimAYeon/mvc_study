package com.info.jsp;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CustomerInfoInput implements CustomerInfoService {
	public void CustomerInfoExecute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		CustomerInfoDAO dao = new CustomerInfoDAOImpl();
		request.setAttribute("list", dao.selectAll());

		HttpSession session = request.getSession();
		if (session.getAttribute("result") != null) {
			String message = (String) session.getAttribute("result");
			request.setAttribute("message", message);
			session.removeAttribute("result");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/CustomerInfoInput.jsp");
		dispatcher.forward(request, response);
	}
}
