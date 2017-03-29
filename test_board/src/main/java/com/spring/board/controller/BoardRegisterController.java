package com.spring.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.board.domain.BoardVO;
import com.spring.board.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardRegisterController {

	@Autowired
	private BoardService service;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void register(Model model, RedirectAttributes rttr) throws Exception {
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerApply(@ModelAttribute BoardVO vo, Model model, RedirectAttributes rttr) throws Exception {
		int result = service.boardRegister(vo);
		rttr.addFlashAttribute("result", result);
		return "redirect:/board/register";
	}
}
