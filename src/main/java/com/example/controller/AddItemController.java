package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.form.AddItemForm;
import com.example.repository.OrderRepository;
import com.example.server.AddItemService;

/**
 * カートに入れる商品情報のコントローラー.
 * 
 * @author adachiryuji
 *
 */
@Controller
@RequestMapping("add-item")
public class AddItemController {
	
	@Autowired
	public AddItemService addItemService;
	
	@Autowired
	public HttpSession session;
	
	/**
	 * 使用するフォームオブジェクトをリクエストスコープに格納する.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public AddItemForm setUpForm() {
		return new AddItemForm();
	}
	
	
	
	@RequestMapping("add-cart")
	public String addCart(AddItemForm form,Integer userId) {

		System.out.println("AddItemForm:"+form);
		
		
		addItemService.add(form,userId,0);
		
		
		return "redirect:/show-cart";
	}
}
