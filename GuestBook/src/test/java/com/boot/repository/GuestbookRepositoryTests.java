package com.boot.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.boot.dto.GuestbookDto;
import com.boot.entity.Guestbook;
import com.boot.entity.QGuestbook;
import com.boot.service.GuestbookService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@SpringBootTest
public class GuestbookRepositoryTests {
	
	@Autowired
	GuestbookService service;
	
	@Autowired
	private GuestBookRepository guestbookRepo;
	
	//300개의 데이터를 저장
	/*		@Test
			public void insertData() {
				IntStream.rangeClosed(1, 300).forEach(i->{
					
					Guestbook guestbook = Guestbook.builder()
							.title("Title..." + i)
							.content("Content..." + i)
							.writer("writer..." + (i % 10))
							.build();
					System.out.println(guestbookRepo.save(guestbook));
				});
			}*/
	
	/*	@Test
		public void updateTest() {
			Optional<GuestBook> result = guestbookRepo.findById(300L);
			
			if(result.isPresent()) {
				GuestBook guestbook = result.get();
				
				guestbook.changeTitle("제목 수정...");
				guestbook.changeContent("내용 수정...");
				
				guestbookRepo.save(guestbook);
			}
		}*/
	
	/*	@Test
		public void testQuery1() {
			
			Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
			 
			//Predicate
			QGuestbook qGuestbook = QGuestbook.guestbook; 
			
			BooleanBuilder booleanBuilder = new BooleanBuilder();
			
			// title like %1%
			BooleanExpression expression = qGuestbook.title.contains("1");
			
			booleanBuilder.and(expression);
			
			Page<Guestbook> result = guestbookRepo.findAll(booleanBuilder, pageable);
			
			result.forEach(guestbook ->{
				System.out.println(guestbook);
			});
		}*/
	
	@Test
	public void testRegister() {
		GuestbookDto guestbookDto = GuestbookDto.builder()
				.title("Sample title.............")
				.content("Sample Content..............")
				.writer("user0")
				.build();
		
		System.out.println(service.register(guestbookDto));
	}
}
