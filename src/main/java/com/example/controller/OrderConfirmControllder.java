package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.server.OrderService;

/**
 * 注文確認情報のコントローラークラス.
 * 
 * @author adachiryuji
 *
 */
@Controller
@RequestMapping("order-confirm")
public class OrderConfirmControllder {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("")
	public String showConfirm(){
		 
	   
	  
	   
		LoginUser loginUser=new LoginUser();
		
	loginUser=(LoginUser)session.getAttribute("loginUser");
	
	
	Order order=orderService.findByUserIdAndStatus(loginUser.getId(),0);
	session.setAttribute("order",order);
	
	return "order_confirm";
	
	}
	
}
