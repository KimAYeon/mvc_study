package com.spring.security.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.security.vo.Member;

@Repository
public class MemberDaoImpl implements MemberDao {
//extends SqlSessionDaoSupport
	
	@Autowired
	private SqlSession sqlSession;
	
	private static String namespace = "com.spring.security.mapper.MemberMapper";
	
	public Member selectMember(String id) {
		System.out.println("id : " + id);
		return sqlSession.selectOne(namespace + ".selectMember", id);
	}

	@Override
	public int loadUserByUsername(String id) {
		return sqlSession.selectOne(namespace + ".loadUserByUsername", id);
	}
}
