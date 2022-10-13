package com.boot.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.dto.BoardDTO;
import com.boot.dto.PageRequestDTO;
import com.boot.dto.PageResultDTO;

@SpringBootTest
public class BoardServiceTest {
	
	@Autowired
	private BoardService service;
	
	/*	@Test
		public void testRegister() {
			BoardDTO dto = BoardDTO.builder()
					.title("한글 테스트")
					.content("테스트 내용")
					.writerEmail("user50@aaa.com")
					.build();
			
			service.register(dto);
		}*/
	
	/*	@Test
		public void testList() {
			PageRequestDTO pageRequestDTO = new PageRequestDTO();
			PageResultDTO<BoardDTO, Object[]> result = service.getList(pageRequestDTO);
			for(BoardDTO boardDTO : result.getDtoList()) {
				System.out.println(boardDTO);
			}
		}*/
	
	/*		@Test
			public void testGet() {
				Long bno = 1L;
				BoardDTO boardDTO = service.get(bno);
				System.out.println(boardDTO);
			}
		
		@Test
		public void testRemove() {
			Long bno = 1L;
			service.removeWithReplies(bno);
		}*/
	
	@Test
	public void testUpdate() {
		BoardDTO dto = BoardDTO.builder()
				.bno(101L)
				.title("업데이트 테스트")
				.content("업데이트 내용")
				.build();
		
		service.modify(dto);
	}
}
