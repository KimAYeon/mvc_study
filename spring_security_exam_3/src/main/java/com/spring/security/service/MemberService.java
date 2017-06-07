package com.spring.security.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.security.dao.MemberDao;
import com.spring.security.vo.Member;

@Service
public class MemberService implements UserDetailsService {
	
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberDao.selectMember(username);
		if(member == null) {
			throw new UsernameNotFoundException("No user found with username" + member.getUsername());
		}
		
		System.out.println("member.getPw() : " + member.getPassword());
		Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
		roles.add(new SimpleGrantedAuthority("ROLE_USER"));
		UserDetails user = new User(username, member.getPassword(), roles);
		System.out.println("UserDetails:"+user.toString());
		return user;
	}

}
