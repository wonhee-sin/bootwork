package com.boot;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.boot.domain.Board;
import com.boot.persistence.BoardRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class QueryMethodTest {

	@Autowired
	private BoardRepository boardRepo;

	// BeforeEach의 dataPrepare()는 테스트 메소드가 실행되기 전에 동작함.. 데이터 200개 저장
	/*	@BeforeEach
		public void dataPrepare() {
			for (int i = 1; i <= 200; i++) {
				Board board = new Board();
				board.setTitle("Test title " + i);
				board.setWriter("tester");
				board.setContent("test content" + i);
				board.setCreateDate(new Date());
				board.setCnt(0L);
	
				boardRepo.save(board);
			}
		}
	
			@Test
			public void testFindByTitle() {
				//findbytitile(keyword) 사용
				List<Board> boardList = boardRepo.findByTitle("Test title 10");
				
				log.info("searching result");
				for(Board board : boardList) {
					log.info("--->" + board.toString());
				}
			}*/

	/*	@Test
		public void testFintByTitleContainigOrContentContaining() {
			List<Board> boardList = boardRepo.findByTitleContainingOrContentContaining("17", "18");
			
			log.info("검색 결과");
			for(Board board : boardList) {
				log.info("--->" + board.toString());
			}
		}*/
	/*	@Test
		public void testFintByTitleContainigOrderBySeqDesc() {
			List<Board> boardList = boardRepo.findByTitleContainingOrderBySeqDesc("18");
			log.info("검색결과");
			for(Board board : boardList) {
				log.info("--->" + board.toString());
			}
		}*/
	
	/*	@Test
		public void testQueryAnnotationTest1() {
			List<Board> boardList = boardRepo.queryAnnotationTest1("10");
	
			log.info("검색 결과");
			for(Board board : boardList) {
				log.info("--->" + board.toString());
			}
		}*/
	
	@Test
	public void testFindByTitleContaining(){
		Pageable paging = PageRequest.of(0, 10, Sort.Direction.DESC, "seq");
		Page<Board> pageInfo = boardRepo.findByTitleContaining("title", paging);
		
		System.out.println("PAGE SIZE : " + pageInfo.getSize());
		System.out.println("TOTAL PAGES : " + pageInfo.getTotalPages());
		System.out.println("PAGE COUNT : " + pageInfo.getTotalElements());
		System.out.println("NEXT : " + pageInfo.nextPageable());
		
		List<Board> boardList = pageInfo.getContent(); 
		
		
		log.info("검색 결과");
		for(Board board : boardList) {
			log.info("--->" + board.toString());
		}
	}

}
