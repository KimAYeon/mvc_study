package com.spring.security.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.spring.security.dao.SecuredObjectDao;

public class SecuredObjectService {
	
	@Autowired
	private SecuredObjectDao securedObjectDao;
	
	public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getUrlAndRole() throws Exception {
		
		LinkedHashMap<RequestMatcher, List<ConfigAttribute>> ret = new LinkedHashMap<RequestMatcher, List<ConfigAttribute>>();
		LinkedHashMap<Object, List<ConfigAttribute>> data = securedObjectDao.getRolesAndResources();
		System.out.println(data);
		
		Set<Object> keys = data.keySet();
		for(Object key : keys) { 
			ret.put((AntPathRequestMatcher)key, data.get(key));
		}
		
		return ret;
	}
}
