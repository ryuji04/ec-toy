package com.example.server;

import java.util.ArrayList;
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
	 * 商品を全件検索する.
	 * 
	 * @return　全件検索された商品(商品を3つずつ表示したいので、lsitの中にlistを入れている)
	 */
	public List<List<Item>> finadAllItem(){
		
		//商品が3つずつ格納されるlistを作成
		List<List<Item>>perfectItemlList=new ArrayList<>();
		
		//商品を3つずつ格納するためのlistを作成
		List<Item> listInThreeItem=new ArrayList<>();
		
		//itemAllListに全ての商品を格納
		List<Item>itemList=itemRepository.findAll();
		
		
		List<List<Item>> itemAll = setItemList(perfectItemlList,listInThreeItem,itemList);
		
		return itemAll;
	}
	
	/**
	 * 曖昧検索をするメソッド.
	 * 
	 * @param name 商品名
	 * @return 検索結果
	 */
	public List<List<Item>> findByname(String name){
		
		//商品が3つずつ格納されるlistを作成
				List<List<Item>>perfectItemlList=new ArrayList<>();
				
				//商品を3つずつ格納するためのlistを作成
				List<Item> listInThreeItem=new ArrayList<>();
		
		List<Item>itemListFromName=itemRepository.findLikeName(name);
		System.out.println("itemListFromName"+itemListFromName);
		
		List<List<Item>> itemAll = setItemList(perfectItemlList,listInThreeItem,itemListFromName);
		
		return itemAll;
		
		
	}
	
	public List<List<Item>> setItemList(List<List<Item>> perfectItemlList,List<Item> listInThreeItem, List<Item>itemList){
		
				
				//全ての商品を取り出す
				for(int i=0;i<itemList.size();i++ ) {
					listInThreeItem.add(itemList.get(i));
					System.out.println("listInThreeItemの中身"+listInThreeItem);
					if((i+1)%3==0) {
						perfectItemlList.add(listInThreeItem);
						listInThreeItem=new ArrayList<>();
					}
					
					if(itemList.size()%3!=0) {
						perfectItemlList.add(listInThreeItem);
					}
					
					System.out.println("listInThreeItemの中身"+listInThreeItem);
							}
				System.out.println("itemAllListの中身"+itemList);
				System.out.println("perfectItemListの中身"+perfectItemlList);
				
				return perfectItemlList;
	}
	
}









