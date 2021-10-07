package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.server.OrderService;

/**
 * カートの中を表示するクラス/
 * 
 * @author adachiryuji
 *
 */
@Controller
@RequestMapping("show-cart")
public class ShowItemCartController {

	@Autowired
	private HttpSession session;

	@Autowired
	public OrderService orderService;

	/**
	 * ログインユーザーIDから注文情報を取得する.
	 * 
	 * @param userId ユーザーID
	 * @return 注文情報
	 */
	@RequestMapping("")
	public String showCart(Integer userId,Model model) {

		LoginUser loginUser = new LoginUser();
		loginUser = (LoginUser) session.getAttribute("loginUser");
		
		if(loginUser==null) {
			return "forward:login-user";
		}

		// OrderRepository orderRepository=new OrderRepository();
		Order order= orderService.findByUserIdAndStatus(loginUser.getId(), 0);
		//orderのなかが空の時、「カートの中が空」と表示する
		
		if(order==null||order.getOrderItemList().isEmpty()) {
			
			model.addAttribute("orderOfNull","カートの中が空です");
		}//else if(order.getOrderItemList().size()==0) {
			//model.addAttribute("orderOfNull","カートの中が空です");
		//}
	else {
		
		
		
		model.addAttribute("order", order);
		}

		return "cart_list";
	}

}
