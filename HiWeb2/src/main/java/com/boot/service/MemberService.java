package com.boot.service;

import com.boot.domain.Member;

public interface MemberService {

	void signUp(Member member);
	
	Member userInfo(Member member);
	
	void deleteAccount(Member member);
	
	boolean checkPassword(Member member, String checkPassword);
}
