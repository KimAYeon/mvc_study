package com.spring.board.persistence;

import java.util.List;

import com.spring.board.domain.BoardVO;
import com.spring.board.domain.Criteria;
import com.spring.board.domain.SearchCriteria;

public interface BoardDAO {
	public int insert(BoardVO vo) throws Exception;
	public List<BoardVO> selectAll() throws Exception;
	public BoardVO select(int bno) throws Exception;
	public int update(BoardVO vo) throws Exception;
	public int updateViewCnt(BoardVO vo) throws Exception;
	public int delete(List<String> bno) throws Exception;
	public int deleteAll() throws Exception;
	public List<BoardVO> listPage(int page) throws Exception;
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception;
	public int listSearchCount(SearchCriteria cri) throws Exception;
	public int insertAttach(String fname) throws Exception;
}
