package com.spring.board.service;

import java.util.List;

import com.spring.board.domain.ReplyVO;

public interface ReplyService {
	public List<ReplyVO> replyList(int bno) throws Exception;
	public List<ReplyVO> reReplyList(int rno) throws Exception;
	public int replyRegister(ReplyVO vo) throws Exception;
	public int replyModify(ReplyVO vo) throws Exception;
	public int replyRemove(int rno) throws Exception;
}
