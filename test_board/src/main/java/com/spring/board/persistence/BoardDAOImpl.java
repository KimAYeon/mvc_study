package com.spring.board.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.domain.BoardVO;
import com.spring.board.domain.Criteria;

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
	public int delete(List<String> bno) throws Exception {
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
	
}
