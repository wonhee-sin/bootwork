package com.shop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.shop.dto.MemberDTO;
import com.shop.entity.Member;

@SpringBootTest
public class MemberServiceTest {
	
	/*	@Autowired
		MemberService service;
		
		@Autowired
		PasswordEncoder passEnc;
		
		//회원 생성
		public Member createMember() {
			MemberDTO dto = new MemberDTO();
			dto.setEmail("admin@test.com");
			dto.setAddress("서울시 구로구");
			dto.setName("관리자");
			dto.setPassword("1234");
			
			return Member.createMember(dto, passEnc);
		}
		
		//회원 저장
			@Test
			public void saveMemberTest() {
				Member member = createMember();
				service.saveMember(member);
			}
		
		//이메일 중복체크
		@Test
		public void saveDuplicateMemberTest() {
			Member member1 = createMember();
			Member member2 = createMember();
			service.saveMember(member1);
			
			Throwable e = assertThrows(IllegalStateException.class, () ->{
				service.saveMember(member2);
			});
			assertEquals("이미 가입된 회원입니다.", e.getMessage());
		}
		*/
}
