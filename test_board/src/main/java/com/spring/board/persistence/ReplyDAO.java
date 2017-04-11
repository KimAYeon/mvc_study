package com.spring.board.persistence;

import java.util.List;

import com.spring.board.domain.ReplyVO;

public interface ReplyDAO {
	public List<ReplyVO> selectReply(int bno) throws Exception;
	public List<ReplyVO> selectReReply(int rno) throws Exception;
	public int insert(ReplyVO vo) throws Exception;
	public int update(ReplyVO vo) throws Exception;
	public int delete(int rno) throws Exception;
}
