package com.example.server;

import java.util.List;

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
	
	/**
	 * ユーザーIDと注文状況から注文情報を表示する.
	 * 
	 * @param userId ユーザーID
	 * @param status　注文状況
	 * @return　注文情報
	 */
	public Order findByUserIdAndStatus(Integer userId,Integer status) {
		return orderRepository.findByUserIdAndStatus(userId, status);
	}
	
	/**
	 * 注文情報を挿入する.
	 * 
	 * @param order 注文情報
	 * @return　注文情報
	 */
	public Order insertOrder(Order order) {
		Order returnOrder=orderRepository.insertOrder(order);
		return returnOrder;
	}
	
	/**
	 * 注文情報を更新する.
	 * 
	 * @param order 注文情報
	 * @param orderId　注文情報ID
	 */
	public void update(Order order,Integer orderId) {
		orderRepository.update(order,orderId);
	}
	
	/**
	 * 注文履歴を表示する.
	 * 
	 * @param userId ユーザーID
	 * @return　注文情報
	 */
	public List<Order> findForHistory(Integer userId) {
		return orderRepository.findForHistory(userId);
	}
}







