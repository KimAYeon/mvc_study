package com.spring.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.board.service.BoardService;


@Controller
@RequestMapping("/board/*")
public class BoardListController {
	
	@Autowired
	private BoardService service;
	
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public void list(Model model, RedirectAttributes rttr) throws Exception {
		model.addAttribute("list", service.boardList());
		model.addAttribute(rttr.getFlashAttributes());
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String listRemove(@RequestParam(value="check") List<String> bnos, RedirectAttributes rttr) throws Exception {
		int resultRemove = service.boardRemove(bnos);
		rttr.addFlashAttribute("resultRemove", resultRemove);
		return "redirect:/board/list";
	}

}
