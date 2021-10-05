package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.server.OrderItemService;

/**
 *カートの商品情報を削除するコントローラークラス.
 * 
 * @author adachiryuji
 *
 */
@Controller
@RequestMapping("delete-cart")
public class DeleteOrderItemController {
	@Autowired
	public OrderItemService orderItemService;
	
	@RequestMapping("delete")
	public String deleteOrderItem(Integer orderItemId) {
		orderItemService.deleteOrderItemAndOrderToppingById(orderItemId);
		
		return "redirect:/show-cart";
	}
}
