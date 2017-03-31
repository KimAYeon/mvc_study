package com.spring.board.persistence;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.board.domain.BoardVO;
import com.spring.board.domain.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BoardDAOTest {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);

	@Inject 
	private BoardDAO dao;
	
	/*@Test
	public void testString() {
		String a = "A";
		String b = new String("A");
		System.out.println(a==b);
		System.out.println(b.equals(a));
		
		String c = a;
		System.out.println(a==c);
		System.out.println(c.equals(a));
		
		String d = "A";
		System.out.println(a==d);
		System.out.println(d.equals(a));
	}
	
	@Test
	public void testInsert() throws Exception {
		BoardVO vo = new BoardVO();
		vo.setTitle("title3");
		vo.setContent("content3");
		vo.setWriter("writer3");
		vo.setBpw("bpw3");
		System.out.println(dao.insert(vo));
	}
	
	@Test
	public void testSelectAll() throws Exception {
		logger.info(dao.selectAll().toString());
	}
	
	@Test
	public void testSelect() throws Exception {
		logger.info(dao.select(2).toString());
	}
	
	@Test
	public void testDelete() throws Exception {
		
	}*/
	
	/*@Test
	public void testListPage() throws Exception {
		int page = 3;
		List<BoardVO> list = dao.listPage(page);
		for(BoardVO boardVO : list) {
			logger.info(boardVO.getBno() + " : " + boardVO.getTitle());
		}
	}*/
	
	@Test
	public void testListCriteria() throws Exception {
		Criteria cri = new Criteria();
		cri.setPage(2);
		cri.setPerPageNum(20);
		
		List<BoardVO> list = dao.listCriteria(cri);
		for(BoardVO boardVO : list) {
			logger.info(boardVO.getBno() + " : " + boardVO.getTitle());
		}
	}
}
