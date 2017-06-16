package com.spring.security.dao;

import com.spring.security.vo.Member;

public interface MemberDao {
	public Member selectMember(String id);
	public int loadUserByUsername(String id);
}
