package com.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.boot.domain.Member;
import com.boot.domain.Role;
import com.boot.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberRepository memberRepo;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Override
	public void signUp(Member member) {
		//비밀번호 암호화
		String encPW = encoder.encode(member.getPassword());
		member.setPassword(encPW);
		//권한 부여
		member.setRole(Role.ROLE_MEMBER);
		
		member.setEnabled(true);
		memberRepo.save(member);
	}

	@Override
	public Member userInfo(Member member) {
		String encPW = encoder.encode(member.getPassword());
		member.setPassword(encPW);
		
		member.setRole(Role.ROLE_MEMBER);
		member.setEnabled(true);
		
		memberRepo.save(member);
		return null;
	}

	@Override
	public void deleteAccount(Member member) {
		memberRepo.delete(member);
		
	}

	@Override
	public boolean checkPassword(Member member, String checkPassword) {
		String realPassword = member.getPassword();
		boolean matches = encoder.matches(checkPassword, realPassword);
		return matches;
	}

}
