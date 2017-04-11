package com.spring.board.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.board.domain.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	
	@Autowired
	private SqlSession session;
	
	private static String namespace = "com.spring.board.mapper.ReplyMapper";

	@Override
	public List<ReplyVO> selectReply(int bno) throws Exception {
		return session.selectList(namespace+".selectReply", bno);
	}

	@Override
	public int insert(ReplyVO vo) throws Exception {
		return session.insert(namespace+".insert", vo);
	}

	@Override
	public int update(ReplyVO vo) throws Exception {
		return session.update(namespace+".update", vo);
	}

	@Override
	public int delete(int rno) throws Exception {
		return session.delete(namespace+".delete", rno);
	}

	@Override
	public List<ReplyVO> selectReReply(int rno) throws Exception {
		return session.selectList(namespace+".selectReReply", rno);
	}

}
