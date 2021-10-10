package com.example.server;

import java.util.List;

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

	/**
	 * 全件検索.
	 * 
	 * @return　商品の全件検索
	 */
	public List<Item> findAll() {
		return itemRepository.findAll();
	}

}
