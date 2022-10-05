package com.boot.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(exclude="boardList")
@Getter
@Setter
@Entity
public class Member {

	@Id
	@Column(name="MEMBER_ID")
	private String id;
	
	private String password;
	
	private String name;
	
	private String role;
	
	@OneToMany(mappedBy="member", fetch=FetchType.EAGER)
	private List<Board> boardList = new ArrayList<>();
}
