package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.server.ItemService;

/**
 * 商品を表示するコントローラー.
 * 
 * @author adachiryuji
 *
 */


@Controller
@RequestMapping("show-item")
public class ShowItemController {
	@Autowired
	private ItemService itemService;
	
	/**
	 * 商品一覧画面を表示する.
	 * 
	 * @return 商品一覧画面へ遷移
	 */
	@RequestMapping("")
	public String index() {
		return "item_list_toy";
	}
	
	@RequestMapping("findAll")

}
