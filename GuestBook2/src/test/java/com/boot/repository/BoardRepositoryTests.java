package com.boot.repository;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.boot.dto.BoardDTO;
import com.boot.dto.PageRequestDTO;
import com.boot.dto.PageResultDTO;
import com.boot.entity.Board;
import com.boot.entity.Member;

@SpringBootTest
public class BoardRepositoryTests {

	@Autowired
	private BoardRepository boardRepository;
	
	
	/*	
			@Test
			public void insertBoard() {
				IntStream.rangeClosed(1, 100).forEach(i -> {
					
					Member member = Member.builder().email("user"+i
							+"@aaa.com").build();
					
					Board board = Board.builder()
							.title("Title...."+i)
							.content("Content...." + i)
							.writer(member)
							.build();
					boardRepository.save(board);
				});
			}*/
	
	/*	@Transactional
		@Test
		public void TesetRead1() {
			Optional<Board> result = boardRepository.findById(100L);
			//데이터베이스에 존재하는 번호
			Board board = result.get();
			System.out.println(board);
			System.out.println(board.getWriter());
		}*/
	
	/*	@Test
		public void testReadWithWriter() {
			Object result = boardRepository.getBoardWithWriter(100L);
			
			Object[] arr = (Object[]) result;
			
			System.out.println(Arrays.toString(arr));
		}*/
	
	/*	@Test
		public void testWithReplyCount() {
			Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
			
			Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);
			
			result.get().forEach(row -> {
				
				Object[] arr = (Object[])row;
				System.out.println(Arrays.toString(arr));
			});
		}*/
	
	/*	@Test
		public void testRead2() {
			Object result = boardRepository.getBoardByBno(100L);
			Object[] arr = (Object[]) result;
			
			System.out.println(Arrays.toString(arr));
		}*/
	/*	
		@Test
		public void TestSearch1() {
			boardRepository.search1();
		}*/
	
	@Test
	public void testSearchPage() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
		Page<Object[]> result = boardRepository.searchPage("t", "1", pageable);
	}
	
	
}
