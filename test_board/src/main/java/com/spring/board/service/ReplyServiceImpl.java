package com.spring.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.domain.ReplyVO;
import com.spring.board.persistence.ReplyDAO;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyDAO dao;

	@Override
	public List<ReplyVO> replyList(int bno) throws Exception {
		return dao.selectReply(bno);
	}

	@Override
	public int replyRegister(ReplyVO vo) throws Exception {
		return dao.insert(vo);
	}

	@Override
	public int replyModify(ReplyVO vo) throws Exception {
		return dao.update(vo);
	}

	@Override
	public int replyRemove(int rno) throws Exception {
		return dao.delete(rno);
	}

	@Override
	public List<ReplyVO> reReplyList(int rno) throws Exception {
		return dao.selectReReply(rno);
	}

}
