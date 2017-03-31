package com.spring.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.board.domain.Criteria;
import com.spring.board.domain.PageVO;
import com.spring.board.service.BoardService;


@Controller
@RequestMapping("/board/*")
public class BoardListController {
	
	@Autowired
	private BoardService service;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model, Criteria cri, RedirectAttributes rttr) throws Exception {
		model.addAttribute("list", service.listCriteria(cri));
		PageVO vo = new PageVO();
		vo.setCri(cri);
		vo.setTotalCount(service.boardList().size());
		model.addAttribute("pageVO", vo);
		model.addAttribute(rttr.getFlashAttributes());
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String listRemove(@RequestParam(value="check") List<String> bnos, RedirectAttributes rttr) throws Exception {
		int resultRemove = service.boardRemove(bnos);
		rttr.addFlashAttribute("resultRemove", resultRemove);
		return "redirect:/board/list";
	}
}
