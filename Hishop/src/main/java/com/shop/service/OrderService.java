package com.shop.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.shop.dto.OrderDTO;
import com.shop.dto.OrderHistDTO;
import com.shop.dto.OrderItemDTO;
import com.shop.entity.Item;
import com.shop.entity.ItemImage;
import com.shop.entity.Member;
import com.shop.entity.OrderItem;
import com.shop.entity.Orders;
import com.shop.repository.ItemImageRepository;
import com.shop.repository.ItemRepository;
import com.shop.repository.MemberRepository;
import com.shop.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class OrderService {
	
	private final ItemRepository itemRepository;

    private final MemberRepository memberRepository;

    private final OrderRepository orderRepository;
    
    private final ItemImageRepository itemImgRepository;
    
    //주문하기
    public Long order(OrderDTO orderDto, String email){
    	//주문할 상품 조회
        Item item = itemRepository.findById(orderDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);

        //로그인한 회원 정보 조회
        Member member = memberRepository.findByEmail(email);

        //회원 정보와 주문할 상품 리스트 정보를 이용하여 주문 엔티티를 생성
        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
        orderItemList.add(orderItem);
        Orders order = Orders.createOrder(member, orderItemList);
        orderRepository.save(order);  //주문 엔티티 저장

        return order.getId();
    }
    
    //구매 이력
    @Transactional(readOnly = true)
    public Page<OrderHistDTO> getOrderList(String email, Pageable pageable) {

        List<Orders> orders = orderRepository.findOrders(email, pageable); //주문 조회
        Long totalCount = orderRepository.countOrder(email);  //주문 총개수

        //주문 리스트를 순회하면서 구매 이력 페이지에 전달할 DTO를 생성함
        List<OrderHistDTO> orderHistDtos = new ArrayList<>();

        for (Orders order : orders) {
            OrderHistDTO orderHistDto = new OrderHistDTO(order);
            List<OrderItem> orderItems = order.getOrderItems();
            for (OrderItem orderItem : orderItems) {
            	//대표 이미지 조회
                ItemImage itemImg = 
                		itemImgRepository.findByItemIdAndRepresentImageYN(orderItem.getItem().getId(), "Y");
                OrderItemDTO orderItemDto =
                        new OrderItemDTO(orderItem, itemImg.getImageUrl());
                orderHistDto.addOrderItemDTO(orderItemDto);
            }

            orderHistDtos.add(orderHistDto);
        }

        //페이지 구현 객체를 생성하여 반환함
        return new PageImpl<OrderHistDTO>(orderHistDtos, pageable, totalCount);
    }
    
    //현재 로그인한 사용자와 주문 데이터를 생성한 사용자가 같은지 검사
    @Transactional(readOnly = true)
    public boolean validateOrder(Long orderId, String email){
        Member curMember = memberRepository.findByEmail(email);
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
        Member savedMember = order.getMember();

        if(!StringUtils.equals(curMember.getEmail(), savedMember.getEmail())){
            return false;
        }

        return true;
    }

    //주문 취소
    public void cancelOrder(Long orderId){
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
        order.cancelOrder();
    }
}
