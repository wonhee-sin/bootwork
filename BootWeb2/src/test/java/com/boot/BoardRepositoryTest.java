package com.boot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.domain.Board;
import com.boot.persistence.BoardRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class BoardRepositoryTest {
	
	@Autowired
	private BoardRepository brepo;
	
	//글 등록
	@Test
	public void testInsertBoard() {
		Board b = new Board();
		b.setTitle("두 번째 게시글");
		b.setWriter("테스터");
		b.setContent("등록이 잘 되네요");
		
		brepo.save(b);
	}
}
