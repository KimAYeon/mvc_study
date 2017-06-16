package com.spring.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
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
	
	/*@Override
	public UserDetails loadUserByUsername(String username) {
		
		Member member = memberDao.selectMember(username);
		
		if(member == null) {
			throw new UsernameNotFoundException("No user found with username");
		} else if(Authentication.class.getName().equals(member.getUsername()) && Authentication.class.getCredentials().equals(member.getUsername())) {
			Collection<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
			roles.add(new SimpleGrantedAuthority(member.getRole()));
			UserDetails user = new User(member.getUsername(), member.getPassword(), roles);
			
			return user;
		} else {
			throw new BadCredentialsException("Bad credentials");
		}
	}*/
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberDao.selectMember(username);
		
		if(member==null) {
			System.out.println("UsernameNotFoundException");
			throw new UsernameNotFoundException("등록된 사용자가 없습니다.");
		}
		
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority(member.getRole()));
	
       return buildUserForAuthentication(member, roles);
    }

    private User buildUserForAuthentication(Member member, List<GrantedAuthority> authorities) {
        return new User(member.getUsername(), member.getPassword(), true, true, true, true, authorities);
    }

}
