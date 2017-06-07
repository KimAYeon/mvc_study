package com.spring.board.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.spring.board.domain.BoardVO;
import com.spring.board.domain.Criteria;
import com.spring.board.domain.SearchCriteria;
import com.spring.board.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO dao;
	
	@Autowired
	private ApplicationContext ac;
	
	@Override
	public int boardRegister(BoardVO vo) throws Exception {
		int result = dao.insert(vo);
		System.out.println(vo);
		String[] files = vo.getFiles();
		if (files != null) {
			for(String fname : files) {
				result = dao.insertAttach(fname, vo.getBno());
			}
		}
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
		String[] files = vo.getFiles();
		List<Object> bnos = new ArrayList<Object>();
		bnos.add(vo.getBno());
		removeAllFiles(bnos);
		if(files != null) {
			for(String fname : files) {
				result = dao.insertAttach(fname, vo.getBno());
			}
		}
		return result;
	}

	@Override
	public int boardRemove(List<Object> bno) throws Exception {
		int result = dao.delete(bno);
		dao.deleteAttach(bno);
		return result;
	}

	@Override
	public int boardViewCntUp(int bno) throws Exception {
		BoardVO vo = dao.select(bno);
		vo.setViewcnt(vo.getViewcnt()+1);
		return dao.updateViewCnt(vo);
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return dao.listCriteria(cri);
	}

	@Override
	public int boardRemoveAll() throws Exception {
		return dao.deleteAll();
	}

	@Override
	public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception {
		return dao.listSearch(cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return dao.listSearchCount(cri);
	}

	@Override
	public List<String> listAttach(int bno) throws Exception {
		return dao.selectAttach(bno);
	}

	@Override
	public int removeAllFiles(List<Object> bno) throws Exception {
		return dao.deleteAttach(bno);
	}

}
