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
		member.setId("admin2");
		member.setPassword(encoder.encode("admin222"));
		member.setName("어드민2");
		member.setRole(Role.ROLE_ADMIN);
		member.setEnabled(true);
		
		memberRepo.save(member);
	}
}
