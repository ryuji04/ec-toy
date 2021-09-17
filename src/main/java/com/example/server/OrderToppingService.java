package com.example.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.domain.Topping;

/**
 * カートに入れるトッピング情報のサービスクラス.
 * 
 * @author adachiryuji
 *
 */
@Service
@Transactional
public class OrderToppingService {
	@Autowired
	public OrderItemService orderItemService;

	public OrderTopping orderTopping(Integer itemId, Integer quantity, Character size, Topping topping) {
		OrderTopping orderTopping = new OrderTopping();
		orderTopping.setToppingId(topping.getId());
		orderTopping.setTopping(topping);

		OrderItem orderItem = orderItemService.insertOrderItem(itemId, quantity, size);
		orderTopping.setOrderItemId(orderItem.getId());

		return orderTopping;

	}

}
