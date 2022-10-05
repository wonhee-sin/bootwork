package com.boot.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.boot.service.BoardUserDetailService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BoardUserDetailService boardUserDetailsService;
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/member/**").authenticated()
			.antMatchers("/manager/**").hasRole("MANAGER")
			.antMatchers("/admin/**").hasRole("ADMIN");
		
		//http.formLogin();
		
		http.csrf().disable();
		
		http.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/loginSuccess", true);
		
		http.exceptionHandling().accessDeniedPage("/accessDenied");
		
		http.logout().invalidateHttpSession(true).logoutSuccessUrl("/login");
		
		http.userDetailsService(boardUserDetailsService);
	}
	
	@Autowired
	public void authenticate(AuthenticationManagerBuilder auth) throws Exception {
		/*		auth.inMemoryAuthentication()
					.withUser("manager")				//사용자 아이디설정
					.password("{noop}manager123")		//비밀번호에 대한 암호화 하지 않음
					.roles("MANAGER");					//권한 설정
				
				auth.inMemoryAuthentication()
					.withUser("admin")				//사용자 아이디설정
					.password("{noop}admin123")		//비밀번호에 대한 암호화 하지 않음
					.roles("ADMIN");					//권한 설정
		*/	
		
		String query1 = "select id username, concat('{noop}', password) "
					+ "password, true enabled from member where id =?";
		String query2 = "select id, role from member where id=?";
		
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery(query1)
			.authoritiesByUsernameQuery(query2);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
