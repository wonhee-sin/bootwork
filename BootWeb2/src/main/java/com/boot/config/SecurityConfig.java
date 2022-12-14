package com.boot.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.boot.service.BoardUserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private BoardUserDetailsService boardUserDetailsService;
	

	
	@Override
	protected void configure(HttpSecurity security) throws Exception {
		
		security.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/", true);
		
		security.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/getBoardList").authenticated();
		
		security.csrf().disable();
		//๋ก๊ทธ์์
		security.logout().invalidateHttpSession(true).logoutSuccessUrl("/");
		
		security.userDetailsService(boardUserDetailsService);
	}
	
	@Autowired
	public void authenticate(AuthenticationManagerBuilder auth) throws Exception {
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
