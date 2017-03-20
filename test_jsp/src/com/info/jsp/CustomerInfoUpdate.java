package com.info.jsp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CustomerInfoUpdate implements CustomerInfoService {

	@Override
	public void CustomerInfoExecute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		CustomerInfoDAO dao = new CustomerInfoDAOImpl();
		CustomerInfoVO vo = new CustomerInfoVO();
		vo.setCustomNo(Integer.parseInt(request.getParameter("customNo")));
		vo.setName(request.getParameter("name"));
		vo.setGender(request.getParameter("gender"));
		vo.setAge(Integer.parseInt(request.getParameter("age")));
		
		int result = dao.update(vo);
		
		HttpSession session = request.getSession(false);
		session.setAttribute("result", "수정 완료되었습니다.");
		
		response.sendRedirect("/customer/input");
	}
	
}
