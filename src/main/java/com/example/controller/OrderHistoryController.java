package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.server.OrderService;

/**
 * 注文履歴のコントローラークラス.
 * 
 * @author adachiryuji
 *
 */
@Controller
@RequestMapping("order-history")
public class OrderHistoryController {
	@Autowired
	private OrderService orderService;

	@Autowired
	private HttpSession session;

	@RequestMapping("")
	public String index() {
		LoginUser loginUser = (LoginUser) (session.getAttribute("loginUser"));
		List<Order> orderList = orderService.findForHistory(loginUser.getId());
		session.setAttribute("orderList", orderList);
		
		/**for(Order order:orderList) {
			for(OrderItem orderItem:order.getOrderItemList()) {
					System.out.println("orderItem"+orderItem);
			}
		}*/

		return "order_history";
	}

}
