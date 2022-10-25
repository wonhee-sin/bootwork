package com.shop.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shop.entity.Member;
import com.shop.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService implements UserDetailsService {
	
	private final MemberRepository repository;
	
	//회원 가입(저장)
	public Member saveMember(Member member) {
		validateDuplicateMember(member);	//중복체크 메서드 호출
		return repository.save(member);
	}
	
	//이메일 중복 체크 메서드
	private void validateDuplicateMember(Member member) {
		Member findMember = repository.findByEmail(member.getEmail());
		if(findMember != null) {
			throw new IllegalStateException("이미 가입된 회원입니다.");
		}
	}

	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Member member = repository.findByEmail(email);
		if(member == null) {
			throw new UsernameNotFoundException(email);
		}
		
		
		return User.builder()
				.username(member.getEmail())
				.password(member.getPassword())
				.roles(member.getRole().toString())
				.build();
	}
}
