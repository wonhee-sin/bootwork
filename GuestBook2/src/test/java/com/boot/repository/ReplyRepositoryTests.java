package com.boot.repository;

import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.entity.Board;
import com.boot.entity.Reply;

@SpringBootTest
public class ReplyRepositoryTests {

	@Autowired
	private ReplyRepository replyRepository;
	
	/*	@Test
		public void insertReply() {
			IntStream.rangeClosed(1, 300).forEach(i->{
				
				long bno = (long) (Math.random() * 100) + 1;
				Board board = Board.builder().bno(bno).build();
				Reply reply = Reply.builder()
						.text("Reply......." + i)
						.board(board)
						.replyer("guest")
						.build();
				replyRepository.save(reply);
			});
		}*/
	
	@Transactional
	@Test
	public void readReply1() {
		Optional<Reply> result = replyRepository.findById(1L);
		Reply reply = result.get();
		System.out.println(reply);
		System.out.println(reply.getBoard());
	}
}
