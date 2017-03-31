package com.spring.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.user.domain.UserVO;
import com.spring.user.service.UserService;

@Controller
@RequestMapping("/user/*")
public class UserJoinController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public void join(Model model, RedirectAttributes rttr) throws Exception {
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	@ResponseBody
	public int joinApply(@ModelAttribute UserVO vo) throws Exception {
		int resultJoin = service.userJoin(vo);
		return resultJoin;
	}
	
	@RequestMapping(value = "/join/idduplchk", method = RequestMethod.POST)
	@ResponseBody
	public UserVO idDuplChk(@RequestParam String uid) throws Exception {
		System.out.println(service.userSearch(uid));
		return service.userSearch(uid);
	}

}
