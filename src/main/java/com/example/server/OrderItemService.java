package com.example.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.OrderItem;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderToppingRepository;

/**
 * 注文する商品情報のサービスクラス.
 * 
 * @author adachiryuji
 *
 */
@Service
@Transactional
public class OrderItemService {
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private OrderToppingRepository orderToppingRepository;
	
	/**
	 * 注文する商品の情報とトッピング情報を削除するメソッド.
	 * 
	 * @param id ID
	 */
	public void deleteOrderItemAndOrderToppingById(Integer id) {
		
		OrderItem orderItem=orderItemRepository.findById(id);
		System.out.println("orderItem:"+orderItem);
		orderToppingRepository.deleteByOrderItemId(orderItem.getId());
		orderItemRepository.deleteById(id);
		
		
		
	}
}
