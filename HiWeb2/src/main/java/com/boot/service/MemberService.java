package com.boot.service;

import com.boot.domain.Member;

public interface MemberService {

	void signUp(Member member);
	
	Member userInfo(String userid);
}
