package com.shop.dto;

import org.modelmapper.ModelMapper;

import com.shop.entity.ItemImage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemImageDTO {
	
	private Long id;
	
	private String imageName;
	
	private String originImageName;

	private String imageUrl;
	
	private String representImageYN;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	//modelMapper를 사용하여 엔티티 객체와 DTO 객체간의 데이터를 복사하여
	//복사한 객체를 반환함
	public static ItemImageDTO of(ItemImage itemImage) {
		return modelMapper.map(itemImage, ItemImageDTO.class);
	}
}
