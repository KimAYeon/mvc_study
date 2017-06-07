package com.spring.board.service;

import java.util.List;

import com.spring.board.domain.BoardVO;
import com.spring.board.domain.Criteria;
import com.spring.board.domain.SearchCriteria;

public interface BoardService {
	public int boardRegister(BoardVO vo) throws Exception;
	public List<BoardVO> boardList() throws Exception;
	public BoardVO boardRead(int bno) throws Exception;
	public int boardModify(BoardVO vo) throws Exception;
	public int boardRemove(List<Object> bno) throws Exception;
	public int boardRemoveAll() throws Exception;
	public int boardViewCntUp(int bno) throws Exception;
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception;
	public int listSearchCount(SearchCriteria cri) throws Exception;
	public List<String> listAttach(int bno) throws Exception;
	public int removeAllFiles(List<Object> bno) throws Exception;
	
}
