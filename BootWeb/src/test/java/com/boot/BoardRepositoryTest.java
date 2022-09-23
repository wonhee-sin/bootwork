package com.boot;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.domain.Board;
import com.boot.persistence.BoardRepository;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class BoardRepositoryTest {

	@Autowired
	private BoardRepository boardRepo;
	
	/*	@Test
		public void testInsertBoard() {
			Board board = new Board();
			board.setTitle("first post");
			board.setWriter("tester");
			board.setContent("it's well to resister");
			board.setCreateDate(new Date());
			board.setCnt(0L);
			
			boardRepo.save(board); //save() 메서드로 db에 저장함
			
		}*/
	
	@Test
	public void testGetBoard() {
		Board board = boardRepo.findById(1L).get();
		log.info(board.toString());
	}
	
	//글 수정
	/*@Test
	public void testUpdateBoard() {
		log.info("2번 게시글 조회");
		
		Board board = boardRepo.findById(2L).get();
		
		log.info("2번 게시블 제목 수정");
		board.setTitle("update the title");
		//save after update
		boardRepo.save(board);
	}*/
	
	/*	@Test
		public void testDeleteBoard() {
			log.info("firts post delete");
			
			boardRepo.deleteById(1L);
		}*/
}
