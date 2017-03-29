package com.spring.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.board.domain.BoardVO;
import com.spring.board.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO dao;

	@Override
	public int boardRegister(BoardVO vo) throws Exception {
		int result = dao.insert(vo);
		return result;
	}

	@Override
	public List<BoardVO> boardList() throws Exception {
		return dao.selectAll();
	}

	@Override
	public BoardVO boardRead(int bno) throws Exception {
		return dao.select(bno);
	}

	@Override
	public int boardModify(BoardVO vo) throws Exception {
		int result = dao.update(vo);
		return result;
	}

	@Override
	public int boardRemove(List<String> bno) throws Exception {
		int result = dao.delete(bno);
		return result;
	}

}
