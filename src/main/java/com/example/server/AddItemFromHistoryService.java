package com.example.server;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.repository.OrderRepository;

@Service
@Transactional
public class AddItemFromHistoryService {

	@Autowired
	private OrderItemService orderItemService;

	@Autowired
	private OrderToppingService orderToppingService;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private HttpSession session;

	/**
	 * 注文履歴から商品を注文する.
	 * 
	 * @param orderItem 注文履歴にある商品情報
	 */
	public void addFromHistoryForInsert(OrderItem orderItem) {
		LoginUser loginUser = (LoginUser) (session.getAttribute("loginUser"));
		

		Order returnOrder = orderRepository.findByUserIdAndStatus(loginUser.getId(), 0);
		
		if (returnOrder == null) {
			Order order = new Order();
			order.setUserId(loginUser.getId());
			order.setStatus(0);
			order.setTotalPrice(0);
			orderRepository.insertOrder(order);
			
			OrderItem orderItem2=new OrderItem();
			orderItem2.setItemId(orderItem.getItemId());
			orderItem2.setOrderId(order.getId());
			orderItem2.setQuantity(orderItem.getQuantity());
			orderItem2.setSize(orderItem.getSize());
			orderItem2.setItem(orderItem.getItem());
			orderItem2.setOrderToppingList(orderItem.getOrderToppingList());
			
			orderItemService.insert(orderItem2);
			
			

			/**
			orderItemService.insert(orderItem);
			
			OrderItem returnOrderItem=orderItemService.findById(orderItem.getId());
			returnOrderItem.setOrderId(order.getId());
			orderItemService.insert(returnOrderItem);
			System.out.println("if:order.getId"+order.getId());
			orderItemService.update(returnOrderItem, returnOrderItem.getOrderId());
			*/

			OrderTopping orderTopping2 =null;
			for (OrderTopping orderTopping : orderItem.getOrderToppingList()) {
				orderTopping2=new OrderTopping();
				orderTopping2.setToppingId(orderTopping.getToppingId());
				orderTopping2.setOrderItemId(orderItem2.getId());
				orderTopping2.setTopping(orderTopping.getTopping());
				orderToppingService.insert(orderTopping2);
			}

		} else {
			
			
			OrderItem orderItem2=new OrderItem();
			orderItem2.setItemId(orderItem.getItemId());
			orderItem2.setOrderId(returnOrder.getId());
			orderItem2.setQuantity(orderItem.getQuantity());
			orderItem2.setSize(orderItem.getSize());
			orderItem2.setItem(orderItem.getItem());
			orderItem2.setOrderToppingList(orderItem.getOrderToppingList());
			
			orderItemService.insert(orderItem2);


			OrderTopping orderTopping2 =null;
			for (OrderTopping orderTopping : orderItem.getOrderToppingList()) {
				orderTopping2=new OrderTopping();
				orderTopping2.setToppingId(orderTopping.getToppingId());
				orderTopping2.setOrderItemId(orderItem2.getId());
				orderTopping2.setTopping(orderTopping.getTopping());
				orderToppingService.insert(orderTopping2);
			}
			
		
		}

	}
}
