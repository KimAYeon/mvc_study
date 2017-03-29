package com.spring.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.user.service.UserService;

@Controller
@RequestMapping("/user/*")
public class UserLogOutController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, Model model, RedirectAttributes rttr) throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("login") != null) {
			session.setAttribute("login", null);
		}
		return "redirect:/board/list";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String loginApply(HttpServletRequest request) throws Exception {
		return "";
	}

}
