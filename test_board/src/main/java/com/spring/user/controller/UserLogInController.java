package com.spring.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.user.service.UserService;

@Controller
@RequestMapping("/user/*")
public class UserLogInController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login(Model model, RedirectAttributes rttr) throws Exception {
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public String loginApply(HttpServletRequest request) throws Exception {
		String resultLogin = service.userLogIn(request.getParameter("uid"), request.getParameter("upw"));
		if(resultLogin=="success") {
			HttpSession session = request.getSession();
			session.setAttribute("login", service.userSearch(request.getParameter("uid")));
		}
		return resultLogin;
	}

}
