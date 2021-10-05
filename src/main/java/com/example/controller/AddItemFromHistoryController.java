package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.OrderItem;
import com.example.server.AddItemFromHistoryService;
import com.example.server.OrderItemService;

/**
 * 注文履歴から商品を注文するコントローラークラス.
 * 
 * @author adachiryuji
 *
 */
@Controller
@RequestMapping("add-item-history")
public class AddItemFromHistoryController {

	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private AddItemFromHistoryService addItemFromHistoryService;

	@RequestMapping("addItemFromHistory")
	public String addItemFromHistory(Integer orderItemId) {

		OrderItem orderItem = orderItemService.findByIdForHistory(orderItemId);
		System.out.println("orderItem::"+orderItem);

		addItemFromHistoryService.addFromHistoryForInsert(orderItem);

		return "forward:/show-item";
	}
}
