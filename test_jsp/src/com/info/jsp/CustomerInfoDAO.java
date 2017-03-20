package com.info.jsp;

import java.sql.Array;
import java.util.ArrayList;

public interface CustomerInfoDAO {
	void insert(CustomerInfoVO vo) throws Exception;
	public ArrayList<CustomerInfoVO> selectAll() throws Exception;
	public int delete(String customNo) throws Exception;
	public int update(CustomerInfoVO vo) throws Exception;
	public CustomerInfoVO select(int customNo) throws Exception;
}
