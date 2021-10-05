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
		orderToppingRepository.deleteByOrderItemId(orderItem.getId());
		orderItemRepository.deleteById(id);
	}
	/**
	 * 注文情報IDから注文情報を取得する.
	 * 
	 * @param orderItemId　注文情報ID
	 * @return 注文情報
	 */
	
	public OrderItem findByIdForHistory(Integer orderItemId) {
		OrderItem orderItem=orderItemRepository.findByIfForHistory(orderItemId);
		
		return orderItem;
	}
	
	/**
	 * 注文情報を挿入する.
	 * 
	 * @param orderItem 注文情報
	 */
	public void insert(OrderItem orderItem) {
		orderItemRepository.insert(orderItem);
	}
	
	public OrderItem findById(Integer orderItemId) {
		return orderItemRepository.findById(orderItemId);
	}
	
	public void update(OrderItem orderItem,Integer orderId) {
		orderItemRepository.update(orderItem, orderId);
	}
	 
}
