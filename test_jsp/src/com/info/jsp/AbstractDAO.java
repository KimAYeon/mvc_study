package com.info.jsp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public abstract class AbstractDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public final void execute() throws Exception {
		try {
			init();
			query();
			conn.commit();
			close();
		} catch (SQLException ex) {
			conn.rollback();
			ex.printStackTrace();
		}
	}
	
	private void init() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://52.79.124.158:3306/test", "testuser", "testuser01!!");
		conn.setAutoCommit(false);
	}
	
	public abstract void query() throws Exception;
	
	private void close() {
		if(rs!=null) try {rs.close();} catch (Exception ex) {}
		if(pstmt!=null) try {pstmt.close();} catch (Exception ex) {}
		if(conn!=null) try {conn.setAutoCommit(true); conn.close();} catch (Exception ex) {}
	}
	
}
