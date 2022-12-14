package com.boot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FileDto {

	private String uuid;
	private String fileName;
	private String contentType;
	
	public FileDto() {}
	
	public FileDto(String uuid, String fileName, String contentType) {
		this.uuid = uuid;
		this.fileName = fileName;
		this.contentType = contentType;
	}
}
