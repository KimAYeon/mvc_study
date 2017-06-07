package com.spring.security.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.security.service.IPTokenBasedRememberMeService;

@Controller
@RequestMapping(value = "")
public class authController {
	
	@Autowired
	private IPTokenBasedRememberMeService ipService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout) {
		ModelAndView model = new ModelAndView();
		
		if(error != null) { model.addObject("error", "Invalid username and password!"); }
		if(logout != null) { model.addObject("msg", "You've been logged out successfully"); }
		
		model.setViewName("login");
		return model;
	}
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView logout(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout) {
		ModelAndView model = new ModelAndView();
		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addObject(user);
		HttpServletRequest context =  ipService.getContext();
		System.out.println("context : " + context);
		/*String key = ipService.getKey();
		String parameter = ipService.getParameter();
		model.addObject(context);*/
		return model;
	}
}
