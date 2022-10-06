package com.boot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.boot.domain.Board;
import com.boot.domain.Member;
import com.boot.domain.Role;
import com.boot.repository.BoardRepository;
import com.boot.repository.MemberRepository;

@SpringBootTest
public class DataInitializeTest {

	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Test
	public void testInsert() {
		//회원 입력
		Member member1 = new Member();
		member1.setUserid("member");
		member1.setPassword(encoder.encode("member123"));
		member1.setName("회원");
		member1.setRole(Role.ROLE_MEMBER);
		member1.setEnabled(true);
		memberRepo.save(member1);
		
		//관리자 입력
		Member member2 = new Member();
		member2.setUserid("admin");
		member2.setPassword(encoder.encode("admin123"));
		member2.setName("관리자");
		member2.setRole(Role.ROLE_ADMIN);
		member2.setEnabled(true);
		memberRepo.save(member2);
		
		//회원이 작성한 게시글 입력
		for(int i=1; i<=13; i++) {
			Board board = new Board();
			board.setMember(member1);
			board.setTitle(member1.getName() + "이 등록한 게시글" + i);
			board.setContent(member1.getName() + "가 등록한 게시글" + i);
			boardRepo.save(board);
		}
		
		//관리자가 작성한 게시글 입력
		for(int i=1; i<=3; i++) {
			Board board = new Board();
			board.setMember(member2);
			board.setTitle(member2.getName() + "이 등록한 게시글" + i);
			board.setContent(member2.getName() + "가 등록한 게시글" + i);
			boardRepo.save(board);
		}
	}
	
	
}
