package com.info.jsp;

import java.io.PrintWriter;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.StringUtils;

public class CustomerInfoDelete implements CustomerInfoService {

	@Override
	public void CustomerInfoExecute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		CustomerInfoDAO dao = new CustomerInfoDAOImpl();
		String customNo = String.join(",", request.getParameterValues("check"));
		int result = dao.delete(customNo);
		
		HttpSession session = request.getSession(false);
		session.setAttribute("result", result + "개의 회원 정보가 삭제되었습니다.");
		
		response.sendRedirect("/customer/input");
	}

}
