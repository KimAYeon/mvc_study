package com.spring.security.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.security.service.IPTokenBasedRememberMeService;

@Controller
public class AuthController {
	
	@Autowired
	private IPTokenBasedRememberMeService ipService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout
			, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		ModelAndView model = new ModelAndView();
		
		if(request.getUserPrincipal() != null) {
			System.out.println(request.getRemoteAddr());
			return "redirect:/" + request.getRemoteAddr();
		}
		if(logout != null) { redirectAttributes.addAttribute("msg", "You've been logged out successfully"); }
		return "/login";
	}
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView welcome(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout) {
		ModelAndView model = new ModelAndView();
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addObject(user);
		/*String key = ipService.getKey();
		String parameter = ipService.getParameter();
		model.addObject(context);*/
		
		//if(!user.getUsername().equals("sdfdfgher5636t")) {
			//throw new Exception("asdf");
		//}
		
		return model;
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView admin(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout) {
		ModelAndView model = new ModelAndView();
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addObject(user);
		HttpServletRequest context =  ipService.getContext();
		System.out.println("context : " + context);
		/*String key = ipService.getKey();
		String parameter = ipService.getParameter();
		model.addObject(context);*/
		return model;
	}
	
	
}
