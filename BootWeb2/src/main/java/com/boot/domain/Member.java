package com.boot.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//클래스(엔티티 = 테이블 역할)

@Entity
@Setter
@Getter
@ToString
public class Member {

	@Id	//pk
	private String id;
	
	private String password;
	private String name;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	private boolean enabled;
}
