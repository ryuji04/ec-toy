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
public class ShowItemService {
	@Autowired
	private ItemRepository itemRepository;
	
	/**
	 * 商品を全件検索する.
	 * 
	 * @return　全件検索された商品(商品を3つずつ表示したいので、lsitの中にlistを入れている)
	 */
	public List<List<Item>> findAllItem(){
		
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
	
	/**
	 * 商品情報を3つずつ格納するメソッド
	 * 
	 * @param perfectItemlList　商品情報リストを3つずつ格納するリスト
	 * @param listInThreeItem　商品情報を3つずつ格納するリスト
	 * @param itemList　商品情報が1つずつ格納されているリスト
	 * @return 商品情報リスト3つずつ格納されたリスト(perfectItemlList)
	 */
	public List<List<Item>> setItemList(List<List<Item>> perfectItemlList,List<Item> listInThreeItem, List<Item>itemList){
		
				
				//全ての商品を取り出す
				for(int i=0;i<itemList.size();i++ ) {
					listInThreeItem.add(itemList.get(i));
					if((i+1)%3==0) {
						perfectItemlList.add(listInThreeItem);
						listInThreeItem=new ArrayList<>();
					}
					
					if(itemList.size()%3!=0) {
						perfectItemlList.add(listInThreeItem);
					}
					
							}
				
				return perfectItemlList;
	}
	
	/**
	 * 降順に商品を検索する.
	 * 
	 * @return 商品情報
	 */
	public  List<List<Item>> sortInDesc() {
		//商品が3つずつ格納されるlistを作成
				List<List<Item>>perfectItemlList=new ArrayList<>();
				
				//商品を3つずつ格納するためのlistを作成
				List<Item> listInThreeItem=new ArrayList<>();
				
				//itemAllListに全ての商品を格納
				List<Item>itemList=itemRepository.sortInDesc();
				
				
				List<List<Item>> itemAll = setItemList(perfectItemlList,listInThreeItem,itemList);
				
				return itemAll;
	}
	
	/**
	 * 昇順に商品を検索する.
	 * 
	 * @return 商品情報
	 */
	public  List<List<Item>> sortInAsc() {
		//商品が3つずつ格納されるlistを作成
		List<List<Item>>perfectItemlList=new ArrayList<>();
		
		//商品を3つずつ格納するためのlistを作成
		List<Item> listInThreeItem=new ArrayList<>();
		
		//itemAllListに全ての商品を格納
		List<Item>itemList=itemRepository.sortInAsc();
		
		
		List<List<Item>> itemAll = setItemList(perfectItemlList,listInThreeItem,itemList);
		
		return itemAll;
	}
}









