package com.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SecurityUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity security) throws Exception {
		
		//직접 저의한 시큐리티 서비스
		security.userDetailsService(userDetailsService);
		
		//인증 처리(로그인과 로그아웃)
		security.formLogin().loginPage("/member/login")
				.defaultSuccessUrl("/");
		
		security.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
				.invalidateHttpSession(true).logoutSuccessUrl("/member/login");
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
