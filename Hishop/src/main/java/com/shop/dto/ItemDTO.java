package com.shop.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemDTO {
	
	private Long id;
	
	@NotBlank(message = "상품명은 필수 입력 값입니다.")
	private String itemName;
	
	@NotNull(message = "가격은 필수 입력 값입니다.")
	private Integer price;
	
	@NotBlank(message = "상세 설명은 필수 입력 값입니다.")
	private String itemDetail;
	
	@NotNull(message = "재고는 필수 입력 값입니다.")
	private Integer stockNumber;
	
	
	private ItemSellStatus itemSellStatus;
	
	//상품 이미지
	private List<ItemImageDTO> itemImageDTOList = new ArrayList<>();
	
	//상품 이미지 아이디 리스트
	private List<Long> itemImageIds = new ArrayList<>();
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	//(dto > entity)
	public Item createItem() {
		return modelMapper.map(this, Item.class);
	}
	
	//entity > dto
	public static ItemDTO of(Item item) {
		return modelMapper.map(item, ItemDTO.class);
	}
}
