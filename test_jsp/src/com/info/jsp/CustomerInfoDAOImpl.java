package com.info.jsp;

import java.sql.Array;
import java.util.ArrayList;

import javax.swing.plaf.synth.SynthSplitPaneUI;

public class CustomerInfoDAOImpl implements CustomerInfoDAO {

	final StringBuffer sql = new StringBuffer();
	private int result;

	public void insert(CustomerInfoVO vo) throws Exception {
		sql.append("INSERT INTO custom");
		sql.append("(name, gender, age)");
		sql.append("VALUES (?, ?, ?)");

		new AbstractDAO() {
			public void query() throws Exception {
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getGender());
				pstmt.setInt(3, vo.getAge());
				pstmt.executeUpdate();
			}
		}.execute();
	}

	@Override
	public int delete(String customNo) throws Exception {
		sql.append("DELETE FROM custom WHERE customNo IN (" + customNo + ")");

		new AbstractDAO() {
			@Override
			public void query() throws Exception {
				pstmt = conn.prepareStatement(sql.toString());
				result = pstmt.executeUpdate();
				System.out.println(result);
			}
		}.execute();

		return result;
	}

	@Override
	public int update(CustomerInfoVO vo) throws Exception {
		sql.append("UPDATE custom SET name=?, gender=?, age=? WHERE customNo=?");

		new AbstractDAO() {
			@Override
			public void query() throws Exception {
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getGender());
				pstmt.setInt(3, vo.getAge());
				pstmt.setInt(4, vo.getCustomNo());
				result = pstmt.executeUpdate();
			}
		}.execute();

		return result;
	}

	@Override
	public ArrayList<CustomerInfoVO> selectAll() throws Exception {
		ArrayList<CustomerInfoVO> list = new ArrayList<>();
		sql.append("SELECT * FROM custom");

		new AbstractDAO() {
			public void query() throws Exception {
				pstmt = conn.prepareStatement(sql.toString());
				rs = pstmt.executeQuery();

				while (rs.next()) {
					CustomerInfoVO vo = new CustomerInfoVO();
					vo.setCustomNo(rs.getInt(1));
					vo.setName(rs.getString(2));
					vo.setGender(rs.getString(3));
					vo.setAge(rs.getInt(4));
					list.add(vo);
				}
			}
		}.execute();
		
		return list;
	}

	@Override
	public CustomerInfoVO select(int customNo) throws Exception {
		CustomerInfoVO vo = new CustomerInfoVO();
		sql.append("SELECT * FROM custom WHERE customNo='" + customNo + "'");

		new AbstractDAO() {
			@Override
			public void query() throws Exception {
				pstmt = conn.prepareStatement(sql.toString());
				rs = pstmt.executeQuery();

				while (rs.next()) {
					System.out.println(rs);
					vo.setCustomNo(rs.getInt(1));
					vo.setName(rs.getString(2));
					vo.setGender(rs.getString(3));
					vo.setAge(rs.getInt(4));
				}
			}
		}.execute();

		return vo;
	}

}
