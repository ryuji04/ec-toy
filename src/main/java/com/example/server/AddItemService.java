package com.example.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.domain.Topping;
import com.example.form.AddItemForm;
import com.example.repository.ItemRepository;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderRepository;
import com.example.repository.OrderToppingRepository;
import com.example.repository.ToppingRepository;

import ch.qos.logback.core.net.SyslogOutputStream;

/**
 * カートに入れる商品情報のサービス.
 * 
 * @author adachiryuji
 *
 */
@Service
@Transactional
public class AddItemService {
	@Autowired
	public OrderRepository orderRepository;

	@Autowired
	public OrderItemRepository orderItemRepository;

	@Autowired
	public OrderToppingRepository orderToppingRepository;

	public void add(AddItemForm form, Integer userId, Integer status) {
		// OrderがあってもなくてもOrderItemはインサートする必要あるからインスタンス化しておく
		OrderItem orderItem = new OrderItem();

		Order returnOrder = orderRepository.findByUserIdAndStatus(userId, status);
		if (returnOrder == null) {
			// Orderに情報をセットする


			Order order = new Order();
			order.setUserId(userId);
			order.setStatus(status);
			order.setTotalPrice(0);


			// OrderItemのgetSubTotal()を使う。今回はまだ作成していない

			Order returnOrder2 = orderRepository.insertOrder(order);

			// OrderItemに情報をセットする.
			orderItem.setItemId(form.getItemId());
			orderItem.setOrderId(returnOrder2.getId());
			orderItem.setQuantity(form.getQuantity());
			orderItem.setSize(form.getSize());
			OrderItem returnOrderItem = orderItemRepository.insert(orderItem);

			// OrderToppingに情報をセットする.
			List<Integer> toppingList = form.getToppingList();
			if (toppingList != null) {
				for (Integer topping : toppingList) {
					OrderTopping orderTopping = new OrderTopping();
					orderTopping.setOrderItemId(returnOrderItem.getId());
					orderTopping.setToppingId(topping);
					orderToppingRepository.insertOrderTopping(orderTopping);
				}
			}

		} else {
			// Orderが見つかったらOrderToppingとOrderItemのみinsertする.

			// OrderItemのinsert
			orderItem.setOrderId(returnOrder.getId());
			orderItem.setItemId(form.getItemId());
			orderItem.setQuantity(form.getQuantity());
			orderItem.setSize(form.getSize());
			OrderItem returnOrderItem = orderItemRepository.insert(orderItem);

			// OrderToppingのinsert
			List<Integer> toppingList = form.getToppingList();
			if (toppingList != null) {
				for (Integer topping : toppingList) {
					OrderTopping orderTopping = new OrderTopping();
					orderTopping.setOrderItemId(returnOrderItem.getId());
					orderTopping.setToppingId(topping);
					orderToppingRepository.insertOrderTopping(orderTopping);
				}
			}
		}
	}
}
