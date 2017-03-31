package com.spring.board.controller;

import java.util.List;

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

import com.spring.board.domain.BoardVO;
import com.spring.board.service.BoardService;

@Controller
@RequestMapping("/board/*")
public class BoardReadController {
	
	@Autowired
	private BoardService service;
	
	@RequestMapping(value = "/read/{bno}", method = RequestMethod.GET)
	public String read(@PathVariable int bno, Model model, RedirectAttributes rttr) throws Exception {
		service.boardViewCntUp(bno);
		model.addAttribute(service.boardRead(bno));
		model.addAttribute(rttr.getFlashAttributes());
		return "/board/read";
	}

	@RequestMapping(value = "/modify/{bno}", method = RequestMethod.POST)
	public String modify(@PathVariable int bno, @ModelAttribute BoardVO vo, RedirectAttributes rttr) throws Exception {
		int resultModify = service.boardModify(vo);
		rttr.addFlashAttribute("resultModify", resultModify);
		return "redirect:/board/read/"+bno;
	}
	
	@RequestMapping(value = "/remove/{bno}", method = RequestMethod.POST)
	public String remove(@PathVariable List<String> bno, RedirectAttributes rttr) throws Exception {
		int resultRemove = service.boardRemove(bno);
		rttr.addFlashAttribute("resultRemove", resultRemove);
		if(resultRemove==0) {
			return "redirect:/board/read/"+bno;
		} else {
			return "redirect:/board/list";
		}
	}
	
	@RequestMapping(value = "/read/pwmatch/{bno}", method = RequestMethod.POST)
	@ResponseBody
	public boolean pwMatch(@PathVariable int bno, @RequestParam String matbpw) throws Exception {
		System.out.println(matbpw);
		if(!matbpw.equals("") && matbpw.equals(service.boardRead(bno).getBpw())) {
			return true;
		}
		return false;
	}
}
