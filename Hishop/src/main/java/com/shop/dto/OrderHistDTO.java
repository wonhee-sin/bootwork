package com.shop.dto;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.shop.constant.OrderStatus;
import com.shop.entity.Orders;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderHistDTO {

	private Long orderId;
	private String orderDate;
	private OrderStatus orderStatus;
	
	public OrderHistDTO(Orders order) {
		this.orderId = order.getId();
        this.orderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.orderStatus = order.getOrderStatus();
	}
	
	//주문 상품 리스트
	private List<OrderItemDTO> orderItemDTOList = new ArrayList<>();
	
	public void addOrderItemDTO(OrderItemDTO orderItemDTO) {
		orderItemDTOList.add(orderItemDTO);
	}
}