package com.example.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.domain.OrderItem;
import com.example.repository.ItemRepository;

/**
 * カートに入れる商品情報のサービス.
 * 
 * @author adachiryuji
 *
 */
@Service
@Transactional
public class OrderItemService {
	@Autowired
	public ItemRepository itemRepository;
	
	public OrderItem insertOrderItem(Integer itemId,Integer quantity,Character size,int userId) {
		
		
		
		
		return orderItem;
		
		
	}
	
}
