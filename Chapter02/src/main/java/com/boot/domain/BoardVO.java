package com.boot.domain;



import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVO {
	private int seq;
	private String title;
	private String writer;
	private String content;
	private Date createDate;
	private int cnt;


}
