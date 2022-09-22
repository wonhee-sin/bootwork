package com.boot;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.domain.Board;
import com.boot.persistence.BoardRepository;

@SpringBootTest
public class BoardRepositoryTest {

	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	public void testInsertBoard() {
		Board board = new Board();
		board.setTitle("first post");
		board.setWriter("tester");
		board.setContent("it's well to resister");
		board.setCreateDate(new Date());
		board.setCnt(0L);
		
		boardRepo.save(board); //save() 메서드로 db에 저장함
		
	}
}
