package com.example.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.domain.Topping;
import com.example.repository.ItemRepository;
import com.example.repository.ToppingRepository;

/**
 * 商品詳細情報のサービスクラス.
 * 
 * @author adachiryuji
 *
 */
@Service
@Transactional
public class ShowItemDetailService {
	@Autowired
	public ItemRepository itemRepository;

	@Autowired
	public ToppingRepository toppingRpository;

	/**
	 * 商品情報を主キー検索するメソッド.
	 * 
	 * @param form 商品情報のフォームクラス
	 * @return 商品情報(トッピング情報を含む)
	 */
	public Item loadItem(Integer id) {
		Item item = itemRepository.load(id);
		
		List<Topping>toppingList=toppingRpository.findAllTopping();
		item.setToppingList(toppingList);
		return item;

	}

}
