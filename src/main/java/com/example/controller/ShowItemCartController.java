package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.repository.OrderRepository;

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
	public OrderRepository orderRepository;

	
	@RequestMapping("")
	public String showCart(Integer userId) {
			
			LoginUser loginUser=new LoginUser();
			loginUser=(LoginUser)session.getAttribute("loginUser");
			
			
			//OrderRepository orderRepository=new OrderRepository();
			Order order=orderRepository.findByUserIdAndStatus(loginUser.getId(),0);
			
			
			session.setAttribute("order", order);
			
			
			return "cart_list";
	}
	
}
