package com.spring.user.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.user.domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@Inject
	private SqlSession session;
	
	private static String namespace = "com.spring.user.mapper.UserMapper";

	@Override
	public UserVO select(String uid) throws Exception {
		return session.selectOne(namespace + ".select", uid);
	}

	@Override
	public int insert(UserVO vo) {
		return session.insert(namespace + ".insert", vo);
	}

	@Override
	public int update(UserVO vo) throws Exception {
		return session.update(namespace + ".update", vo);
	}

	@Override
	public int delete(String uid) throws Exception {
		return session.delete(namespace + ".delete", uid);
	}

}
