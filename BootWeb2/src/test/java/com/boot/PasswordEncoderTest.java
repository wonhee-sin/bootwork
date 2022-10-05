package com.boot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.boot.domain.Member;
import com.boot.domain.Role;
import com.boot.persistence.MemberRepository;

@SpringBootTest
public class PasswordEncoderTest {
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Test
	public void testInsert() {
		Member member = new Member();
		member.setId("member1");
		member.setPassword(encoder.encode("member123"));
		member.setName("멤버");
		member.setRole(Role.ROLE_MEMBER);
		member.setEnabled(true);
		
		memberRepo.save(member);
	}
}
