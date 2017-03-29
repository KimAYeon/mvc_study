package com.spring.user.persistence;

import com.spring.user.domain.UserVO;

public interface UserDAO {
	public UserVO select(String uid) throws Exception;
	public int insert(UserVO vo);
	public int update(UserVO vo) throws Exception;
	public int delete(String uid) throws Exception;
}
