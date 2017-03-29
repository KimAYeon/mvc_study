package com.spring.board.persistence;

import java.util.List;

import com.spring.board.domain.BoardVO;

public interface BoardDAO {
	public int insert(BoardVO vo) throws Exception;
	public List<BoardVO> selectAll() throws Exception;
	public BoardVO select(int bno) throws Exception;
	public int update(BoardVO vo) throws Exception;
	public int delete(List<String> bno) throws Exception;
}
