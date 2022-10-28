package com.shop.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/*
 * Spring Data Jpa는 Auditing 기능을 제공하여 엔티티가 저장 또는 수정될때
 * 자동으로 등록일, 수정일, 등록자, 수정자를 입력해준다.
 * Audit는 사전적 의미는 "감시하다"이다. 즉 엔티티의 생성과 수정을 감시하고 있는 것이다.
 * 이런 공통 멤버 변수들을 추상 클래스로 만들고, 해당 추상 클래스를 상속받는 형태로 엔티티를 
 * 리팩토링 하여 사용한다.
 */
public class AuditorAwareImpl implements AuditorAware<String>{

	@Override
	public Optional<String> getCurrentAuditor() {
		//현재 로그인한 사용자의 정보를 조회하여 사용자의 이름을 등록자와 수정자로 지정함
		Authentication authentication =
				SecurityContextHolder.getContext().getAuthentication();
		String userId = "";
		if(authentication != null) { 
			userId = authentication.getName();
		}
		return Optional.of(userId);
	}

}
