package com.spring.board.service;

import java.util.List;

import com.spring.board.domain.BoardVO;

public interface BoardService {
	public int boardRegister(BoardVO vo) throws Exception;
	public List<BoardVO> boardList() throws Exception;
	public BoardVO boardRead(int bno) throws Exception;
	public int boardModify(BoardVO vo) throws Exception;
	public int boardRemove(List<String> bno) throws Exception;
}
