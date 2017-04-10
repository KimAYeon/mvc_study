package com.spring.board.persistence;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.board.domain.ReplyVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class ReplyDAOTest {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);

	@Inject 
	private ReplyDAO dao;
	
	@Test
	public void register() {
		ReplyVO vo = new ReplyVO();
		vo.setBno(488);
		vo.setReplytext("댓글 추가");
		vo.setReplyer("마구리");
		try {
			dao.insert(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
