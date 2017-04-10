package com.spring.board.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.domain.BoardVO;
import com.spring.board.domain.Criteria;
import com.spring.board.domain.SearchCriteria;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Autowired
	private SqlSession session;
	
	private static String namespace = "com.spring.board.mapper.BoardMapper";

	@Override
	public int insert(BoardVO vo) throws Exception {
		int result = session.insert(namespace+".insert", vo);
		return result;
	}

	@Override
	public List<BoardVO> selectAll() throws Exception {
		return session.selectList(namespace+".selectAll");
	}

	@Override
	public BoardVO select(int bno) throws Exception {
		return session.selectOne(namespace+".select", bno);
	}

	@Override
	public int update(BoardVO vo) throws Exception {
		int result = session.update(namespace+".update", vo);
		return result;
	}

	@Override
	public int delete(List<Object> bno) throws Exception {
		int result = session.delete(namespace+".delete", bno);
		return result;
	}

	@Override
	public List<BoardVO> listPage(int page) throws Exception {
		if(page <= 0) {
			page = 1;
		}
		page = (page - 1) * 10;
		return session.selectList(namespace+".listPage", page);
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return session.selectList(namespace+".listCriteria", cri);
	}

	@Override
	public int updateViewCnt(BoardVO vo) throws Exception {
		return session.update(namespace+".updateViewCnt", vo);
	}

	@Override
	public int deleteAll() throws Exception {
		return session.delete(namespace+".deleteAll");
	}

	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
		return session.selectList(namespace+".selectSearchList", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return session.selectOne(namespace+".selectSearchCount", cri);
	}
	
	@Override
	public List<String> selectAttach(int bno) throws Exception {
		return session.selectList(namespace+".selectAttach", bno);
	}

	@Override
	public int deleteAttach(List<Object> bno) throws Exception {
		return session.delete(namespace+".deleteAttach", bno);
	}

	@Override
	public int insertAttach(String fname, int bno) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bno", bno);
		map.put("fname", fname);
		return session.insert(namespace+".insertAttach", map);
	}
	
}
