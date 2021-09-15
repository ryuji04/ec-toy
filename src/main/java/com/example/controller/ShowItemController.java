package com.example.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
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
	public String findAllItem(Model model) {
		List<List<Item>>itemAllList=itemService.finadAllItem();
		model.addAttribute("itemAllList", itemAllList);
		
		return "item_list_toy";
	}
	
	@RequestMapping("findByName")
	public String findByName(String name,Model model) {
		List<List<Item>>itemAllList=itemService.findByname(name);
		model.addAttribute("itemAllList", itemAllList);
		
		return "item_list_toy";
	}
	

}
