package com.spring.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.board.domain.ReplyVO;
import com.spring.board.service.ReplyService;

@RestController
@RequestMapping("/board/*")
public class ReplyController {

	@Autowired
	private ReplyService service;
	
	@RequestMapping(value = "/reply/list/{bno}", method = RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> replyList(@PathVariable("bno") int bno) {
		
		ResponseEntity<List<ReplyVO>> entity = null;
		try {
			entity = new ResponseEntity<>(service.replyList(bno), HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value = "/reply/register", method = RequestMethod.POST)
	public ResponseEntity<String> replyRegister(@RequestBody ReplyVO vo) {
		
		ResponseEntity<String> entity = null;
		try {
			service.replyRegister(vo);
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value = "/reply/modify", method = RequestMethod.POST)
	public ResponseEntity<String> replyModify(@RequestBody ReplyVO vo) {
		
		ResponseEntity<String> entity = null;
		try {
			service.replyModify(vo);
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
