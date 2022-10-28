package com.shop.repository;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;

@SpringBootTest
public class ItemRepositoryTests {
	/*	
		@Autowired
		ItemRepository repository;
		
		@Test
		public void createItemTest() {
			Item item = new Item();
			item.setItemName("바지");
			item.setPrice(1000);
			item.setItemDetail("테스트상품123");
			item.setItemSellStatus(ItemSellStatus.SELL);
			item.setStockNumber(100);
			repository.save(item);
	
		}*/
}
