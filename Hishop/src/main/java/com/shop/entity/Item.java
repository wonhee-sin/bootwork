package com.shop.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.shop.constant.ItemSellStatus;
import com.shop.dto.ItemDTO;
import com.shop.exception.OutOfStockException;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
public class Item extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "item_id")
	private Long id;
	
	@Column(nullable = false, length = 50)
	private String itemName;
	
	@Column(nullable = false)
	private int price;
	
	@Column(nullable = false)
	private int stockNumber;
	
	@Lob
	@Column(nullable = false)
	private String itemDetail;
	
	@Enumerated(EnumType.STRING)
	private ItemSellStatus itemSellStatus;
	
	
	//상품 수정 메서드
	public void updateItem(ItemDTO dto) {
		this.itemName = dto.getItemName();
		this.price = dto.getPrice();
		this.stockNumber = dto.getStockNumber();
		this.itemDetail = dto.getItemDetail();
		this.itemSellStatus = dto.getItemSellStatus();
	}
	
	//재고를 감소시키는 메서드
	public void removeStock(int stockNumber) {
		int restStock = this.stockNumber -stockNumber;
		if(restStock < 0 ) {
			throw new OutOfStockException("상품의 재고가 부족합니다. (현재 재고 수량 : " + this.stockNumber +")");
		}
		this.stockNumber = restStock;
	}
	
	//재고 증가
	public void addStock(int stockNumber) {
		this.stockNumber += stockNumber;
	}
}
