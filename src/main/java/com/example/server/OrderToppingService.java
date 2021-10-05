package com.example.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.OrderTopping;
import com.example.repository.OrderToppingRepository;

/**
 * 注文するトッピング情報のサービスクラス.
 * 
 * @author adachiryuji
 *
 */
@Service
@Transactional
public class OrderToppingService {

	@Autowired
	private OrderToppingRepository orderToppingRepository;
	
	public void insert(OrderTopping orderTopping) {
		orderToppingRepository.insertOrderTopping(orderTopping);
	}
}
