package com.boot;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.domain.Board;
import com.boot.persistence.BoardRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class BoardRepositoryTest {

	@Autowired
	private BoardRepository boardRepo;
	
	//글 등록
	@Test
	public void testInsertBoard() {
		Board board = new Board();
		board.setTitle("첫 번째 게시글");
		board.setWriter("테스터");
		board.setContent("등록이 잘 되네요..");
		
		boardRepo.save(board);
	}
}
