package com.boot;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.domain.Board;
import com.boot.persistence.BoardRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class QueryMethodTest {
	
	@Autowired
	private BoardRepository boardRepo;
	
	//BeforeEach의 dataPrepare()는 테스트 메소드가 실행되기 전에 동작함.. 데이터 200개 저장
	/*	@BeforeEach
		public void dataPrepare() {
			for (int i=1; i<=200; i++) {
				Board board = new Board();
				board.setTitle("Test title " + i);
				board.setWriter("tester");
				board.setContent("test content" + i);
				board.setCreateDate(new Date());
				board.setCnt(0L);
				
				boardRepo.save(board);
			}
		}*/
	
	@Test
	public void testFindByTitle() {
		//findbytitile(keyword) 사용
		List<Board> boardList = boardRepo.findByTitle("Test title 10");
		
		log.info("searching result");
		for(Board board : boardList) {
			log.info("--->" + board.toString());
		}
	}
}
