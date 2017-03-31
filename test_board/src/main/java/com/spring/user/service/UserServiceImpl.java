package com.spring.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.spring.user.domain.UserVO;
import com.spring.user.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO dao;
	
	@Override
	public UserVO userSearch(String uid) throws Exception {
		return dao.select(uid);
	}

	@Override
	public String userLogIn(String uid, String upw) throws Exception {
		if(userSearch(uid) == null || !upw.equals(userSearch(uid).getUpw())) {
			return "nomatch";
		}
		return "success";
	}

	@Override
	public int userLogOut(String uid) throws Exception {
		return 0;
	}

	@Override
	public int userJoin(UserVO vo) throws Exception {
		int r = 0;
		try {
			r = dao.insert(vo);
		} catch(DuplicateKeyException e) {
			System.out.println(e.getMessage());
			throw new Exception("아이디 중복");
		}
		return r;
	}

	@Override
	public int userModify(UserVO vo) throws Exception {
		
		
		return dao.update(vo);
	}

	@Override
	public int userLeave(String uid) throws Exception {
		return dao.delete(uid);
	}

}
