package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.form.ItemForm;
import com.example.server.ShowItemDetailService;

/**
 * 商品詳細情報のコントローラー
 * 
 * @author adachiryuji
 *
 */
@Controller
@RequestMapping("show-itemDetail")
public class ShowItemDetailController {
	@Autowired
	public ShowItemDetailService showItemDetailService ;
	
	
	@RequestMapping("")
	public String showItemDetail(Model model,ItemForm form) {
		Item item=showItemDetailService.loadItem(form.getId());
		
		model.addAttribute("item", item);
		
		return "item_detail.html";
		
	}
	
}
