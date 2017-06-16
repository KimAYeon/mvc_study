package com.spring.security.dao;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Repository;

@Repository
public class SecuredObjectDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static String namespace = "com.spring.security.mapper.MemberMapper";
	
	public LinkedHashMap<Object, List<ConfigAttribute>> getRolesAndResources() throws Exception {
		System.out.println("SecuredObjectDao");
		
		LinkedHashMap<Object, List<ConfigAttribute>> resourceMap = new LinkedHashMap<Object, List<ConfigAttribute>>();
		List<Map<String, Object>> resultList = sqlSession.selectList(namespace + ".selectUrlAndRole");
		System.out.println(resultList);
		
		Iterator<Map<String, Object>> itr = resultList.iterator();
		Map<String, Object> tempMap;
		String preResource = null;
		String presentResourceStr;
		Object presentResource;
		
		while(itr.hasNext()) {
			tempMap = itr.next();
			
			presentResourceStr = (String) tempMap.get("url");
			presentResource = new AntPathRequestMatcher(presentResourceStr);
			
			List<ConfigAttribute> configList = new LinkedList<ConfigAttribute>();
			
			if(preResource != null && presentResourceStr.equals(preResource)) {
				List<ConfigAttribute> preAuthList = resourceMap.get(presentResource);
				Iterator<ConfigAttribute> preAuthItr = preAuthList.iterator();
				while(preAuthItr.hasNext()) {
					SecurityConfig tempConfig = (SecurityConfig) preAuthItr.next();
					configList.add(tempConfig);
				}
			}
			
			configList.add(new SecurityConfig((String) tempMap.get("role")));
			resourceMap.put(presentResource, configList);
			preResource = presentResourceStr;
		}
		
		return resourceMap;
 	}
}
