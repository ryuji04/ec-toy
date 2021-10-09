package com.example.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Item;
import com.example.repository.ItemRepository;

/**
 * 商品情報のサービスクラス.
 * 
 * @author adachiryuji
 *
 */
@Service
@Transactional
public class ItemService {
	@Autowired
	private ItemRepository itemRepository;
	
	/**
	 * 商品情報をインザートする.
	 * 
	 * @param item 商品情報
	 */
	public void insertItem(Item item) {
		
		itemRepository.insert(item);
	}
	
}
