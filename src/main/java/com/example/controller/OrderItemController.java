package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * カートに入れる商品情報のコントローラー.
 * 
 * @author adachiryuji
 *
 */
@Controller
@RequestMapping("order-item")
public class OrderItemController {
		//@Autowiredを記述
	
	@RequestMapping("")
	public String index() {
		
	}
}
