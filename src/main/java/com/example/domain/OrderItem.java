package com.example.domain;

import java.util.List;

/**
 * カートに入れる商品情報.
 * 
 * @author adachiryuji
 *
 */
public class OrderItem {
	private Integer id; 
	private Integer itemId;
	private Integer orderId;
	private Integer quantity;
	private Character size;
	private Item item;
	private List<OrderTopping>orderToppingList;
	
	public OrderItem() {
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Character getSize() {
		return size;
	}
	public void setSize(Character size) {
		this.size = size;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public List<OrderTopping> getOrderToppingList() {
		return orderToppingList;
	}
	public void setOrderToppingList(List<OrderTopping> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}
	
	
	public int getSubTotal() {
		int itemPrice=0;
		int toppingPrice=0;
		if(size=='M') {
			itemPrice=item.getPrice_m();
		}
		if(size=='L') {
			itemPrice=item.getPrice_l();
		}
		
		List<OrderTopping>orderToppingList2=orderToppingList;
		for(OrderTopping orderTopping:orderToppingList2) {
			Topping topping=orderTopping.getTopping();
			if(size=='M') {
				toppingPrice+=topping.getPrice_m();
			}
			if(size=='L') {
				toppingPrice+=topping.getPrice_l();
			}
		}
		return (itemPrice*quantity)+toppingPrice;
		
	}
	
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", itemId=" + itemId + ", orderId=" + orderId + ", quantity=" + quantity
				+ ", size=" + size + ", item=" + item + ", orderToppingList=" + orderToppingList + "]";
	}
	
	
	

}
