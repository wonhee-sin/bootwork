package com.boot.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.dto.BoardDTO;

@SpringBootTest
public class BoardServiceTest {
	
	@Autowired
	private BoardService service;
	
	@Test
	public void testRegister() {
		BoardDTO dto = BoardDTO.builder()
				.title("한글 테스트")
				.content("테스트 내용")
				.writerEmail("user50@aaa.com")
				.build();
		
		service.register(dto);
	}
}
