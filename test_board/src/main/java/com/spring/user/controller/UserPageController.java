package com.spring.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.user.domain.UserVO;
import com.spring.user.service.UserService;

@Controller
@RequestMapping("/user/*")
public class UserPageController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(value = "/mypage/{uid}", method = RequestMethod.GET)
	public String mypage(HttpServletRequest request, Model model, RedirectAttributes rttr) throws Exception {
		model.addAttribute(rttr.getFlashAttributes());
		return "/user/mypage";
	}

	@RequestMapping(value = "/modify/{uid}", method = RequestMethod.POST)
	public String modify(HttpServletRequest request, @PathVariable String uid, @ModelAttribute UserVO vo, RedirectAttributes rttr) throws Exception {
		vo.setUid(uid);
		int resultModify = service.userModify(vo);
		HttpSession session = request.getSession();
		session.setAttribute("login", service.userSearch(uid));
		rttr.addFlashAttribute("resultModify", resultModify);
		return "redirect:/user/mypage/"+uid;
	}
	
	@RequestMapping(value = "/leave/{uid}", method = RequestMethod.POST)
	public String leave(HttpServletRequest request, @PathVariable String uid, RedirectAttributes rttr) throws Exception {
		int resultLeave = service.userLeave(uid);
		HttpSession session = request.getSession();
		session.invalidate();
		rttr.addFlashAttribute("resultLeave", resultLeave);
		if(resultLeave==0) {
			return "redirect:/user/mypage/"+uid;
		} else {
			return "redirect:/board/list";
		}
	}
	
	@RequestMapping(value = "/mypage/pwmatch/{uid}", method = RequestMethod.POST)
	@ResponseBody
	public boolean pwMatch(@PathVariable String uid, @RequestParam String matupw) throws Exception {
		if(matupw.equals(service.userSearch(uid).getUpw())) {
			return true;
		}
		return false;
	}

}
