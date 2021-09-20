package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Item;
import com.example.domain.OrderItem;
import com.example.server.Lisr;
import com.example.server.Order;
import com.example.server.order;

/**
 * カートに入れる商品情報のコントローラー.
 * 
 * @author adachiryuji
 *
 */
@Controller
@RequestMapping("order-item")
public class OrderItemController {
		//@Autowiredを記述
	
	@RequestMapping("")
	public String index(String itemId,String size,String quantity,String[] toppingId,int userId) {
		//引数を受け取れているか確認
		System.out.println("----------------------");
		System.out.println("itemId:"+itemId);
		System.out.println("size:"+size);
		System.out.println("quantity:"+quantity);
		System.out.println("toppingId:"+toppingId);
		
		
		OrderItem orderItem=new OrderItem();
		orderItem.setItemId(itemId);
		
		//itemIdはhiddenで送ります
		Item item=itemRepository.load(itemId);
		orderItem.setItem(item);
		
		//リクエストパラメーターでvalueを送ります
		orderItem.setQuantity(quantity);
		//リクエストパラメーターでvalueを送ります
		orderItem.setSize(size);
		
		
		
		//最初に、Orderテーブルを作成する。この時、userIdのみを挿入している。
		//orderRepositoryを作成する際は最初の一行だけをreturnで返す
				Order order=orderRepository.findByUserId(userId);
						if(order==null)					
						{
							//hiddenでユーザーIDを飛ばす
							Order order=new Order();
							order.setUserId(userId);
							orderRepository.insert(order);
							//orderIdをセットする
							orderItem.setOrderId(order.getId());
						}else {
							//orderIdをセットする
							orderItem.setOrderId(order.getId());
						}
						
		//orderToppingListに
		
		
		
		return "cart_list";
	}
}
