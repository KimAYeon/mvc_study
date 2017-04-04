package com.spring.board.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.board.domain.PageVO;
import com.spring.board.domain.SearchCriteria;
import com.spring.board.service.BoardService;


@Controller
@RequestMapping("/board/*")
public class BoardListController {
	
	private static final Logger logger = LoggerFactory.getLogger(SearchBoardController.class);
	
	@Autowired
	private BoardService service;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model, SearchCriteria cri, RedirectAttributes rttr) throws Exception {
		System.out.println("board list count : " + service.boardList().size());
		logger.info(cri.toString());
		
		model.addAttribute("list", service.listSearchCriteria(cri));
		
		PageVO pageVO = new PageVO();
		pageVO.setCri(cri);
		pageVO.setTotalCount(service.listSearchCount(cri));
		
		model.addAttribute("pageVO", pageVO);
		model.addAttribute(rttr.getFlashAttributes());
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String listRemove(@RequestParam(value="check") List<String> bnos, RedirectAttributes rttr) throws Exception {
		int resultRemove = service.boardRemove(bnos);
		rttr.addFlashAttribute("resultRemove", resultRemove);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "/removeAll", method = RequestMethod.GET)
	public String listRemoveAll(RedirectAttributes rttr) throws Exception {
		int resultRemove = service.boardRemoveAll();
		rttr.addFlashAttribute("resultRemove", resultRemove);
		return "redirect:/board/list";
	}
}
