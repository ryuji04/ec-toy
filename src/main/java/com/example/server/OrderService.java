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
	
	/**
	 * ユーザーIdから注文情報を取得する.
	 * 
	 * @param userId ユーザーId
	 * @return 注文情報
	 */
	public Order findByUserId(Integer userId) {
		return orderRepository.findByUserId(userId);
	}
	
	public Order findByUserIdAndStatus(Integer userId,Integer status) {
		return orderRepository.findByUserIdAndStatus(userId, status);
	}
}







