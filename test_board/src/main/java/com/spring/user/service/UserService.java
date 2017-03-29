package com.spring.user.service;

import com.spring.user.domain.UserVO;

public interface UserService {
	public UserVO userSearch(String uid) throws Exception;
	public String userLogIn(String uid, String upw) throws Exception;
	public int userLogOut(String uid) throws Exception;
	public int userJoin(UserVO vo) throws Exception;
	public int userModify(UserVO vo) throws Exception;
	public int userLeave(String uid) throws Exception;
}
