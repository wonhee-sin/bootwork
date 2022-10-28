package com.shop.dto;

import com.shop.entity.OrderItem;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderItemDTO {

	private String itemName;
	
	private int count;
	
	private int orderPrice;
	
	private String imageUrl;
	
	public OrderItemDTO(OrderItem orderItem, String imageUrl) {
		this.itemName = orderItem.getItem().getItemName();
		this.count = orderItem.getCount();
		this.orderPrice = orderItem.getOrderPrice();
		this.imageUrl = imageUrl;
	}
}
