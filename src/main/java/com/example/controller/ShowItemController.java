package com.example.controller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.form.SortItemForm;
import com.example.server.ShowItemService;

/**
 * 商品を表示するコントローラー.
 * 
 * @author adachiryuji
 *
 */


@Controller
@RequestMapping("show-item")
public class ShowItemController {
	
	@ModelAttribute
	public SortItemForm setUpForm() {
		return new SortItemForm();
	}
	
	@Autowired
	private ShowItemService itemService;
	
	/**
	 * 商品一覧画面を表示する.
	 * 
	 * @return 商品一覧画面へ遷移
	 */
	@RequestMapping("")
	public String findAllItem(Model model) {
		List<List<Item>>itemAllList=itemService.findAllItem();
		model.addAttribute("itemAllList", itemAllList);
		
		return "item_list_toy";
	}
	
	/**
	 * 	商品名検索をするメソッド.
	 * 
	 * @param name  商品名
	 * @param model　リクエストスコープ
	 * @return　商品一覧画面へ遷移
	 */
	@RequestMapping("findByName")
	public String findByName(String name,Model model) {
		List<List<Item>>itemAllList=itemService.findByname(name);
		model.addAttribute("itemAllList", itemAllList);
		
		return "item_list_toy";
	}
	
	/**
	 * @param sortValue 商品を並び替える.
	 * @param model リクエストスコープ
	 * @return　商品情報
	 */
	@RequestMapping("sortItem")
	public String sortItem(SortItemForm form,Model model) {
		System.out.println("form.get:"+form.getSortItem());
		List<List<Item>> itemAllList=new ArrayList<>();
		
		if(form.getSortItem()==0) {
			itemAllList=itemService.findAllItem();
		}else if(form.getSortItem()==1) {
			itemAllList=itemService.sortInDesc();
			model.addAttribute("itemAllList", itemAllList);
		}else if(form.getSortItem()==2) {
			itemAllList=itemService.sortInAsc();
			model.addAttribute("itemAllList", itemAllList);
		}
		return "item_list_toy";
		
	}
	

}
