package com.example.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.repository.OrderRepository;

/**
 * 注文情報のサービスクラス.
 * 
 * @author adachiryuji
 *
 */
@Repository
@Transactional
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	
	public Order findByUserId(Integer userId) {
		return orderRepository.findByUserId(userId);
	}
}
