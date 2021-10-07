package com.example.form;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.example.domain.OrderItem;
import com.example.domain.User;

/**
 * 注文情報のフォームクラス.
 * 
 * @author adachiryuji
 *
 */
public class OrderForm {
	private Integer id;
	private Integer userId;
	private Integer status;
	private Integer totalPrice;
	@NotBlank(message="配達日時は必須です")
	private String orderDate;
	@NotBlank(message="名前は必須です")
	private String destinationName;
	@NotBlank(message="Emailは必須です")
	//@Email(message="Email形式で入力して下さい")
	private String destinationEmail;
	@NotBlank(message="郵便番号は必須です")
	@Pattern(regexp="^[0-9]{3}-[0-9]{4}$",message="XXX-XXXXの形式で入力願います")
	private String destinationZipcode;
	@NotBlank(message="住所は必須です")
	private String destinationAddress;
	@NotBlank(message="電話番号は必須です")
	@Pattern(regexp="^[0-9]{3}-[0-9]{4}-[0-9]{4}$",message="XXX-XXXX-XXXXの形式で入力願います")
	private String destinationTel;
	private String deliveryTime;
	private Integer paymentMethod;
	private User user;
	private List<OrderItem>orderItemList;
	
	public OrderForm() {
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getDestinationName() {
		return destinationName;
	}
	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}
	public String getDestinationEmail() {
		return destinationEmail;
	}
	public void setDestinationEmail(String destinationEmail) {
		this.destinationEmail = destinationEmail;
	}
	public String getDestinationZipcode() {
		return destinationZipcode;
	}
	public void setDestinationZipcode(String destinationZipcode) {
		this.destinationZipcode = destinationZipcode;
	}
	public String getDestinationAddress() {
		return destinationAddress;
	}
	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}
	public String getDestinationTel() {
		return destinationTel;
	}
	public void setDestinationTel(String destinationTel) {
		this.destinationTel = destinationTel;
	}
	public String getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public Integer getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}
	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}
	
	//消費税10を返すメソッド
	public int getTax() {
		int tax=0;
		for(OrderItem orderItem:orderItemList) {
			tax=tax+orderItem.getSubTotal()/10;
		}
		return tax;
	}
	
	public int getCalcTotalPrice() {
		//商品価格とトッピング価格の合計額
		int totalPrice=0;
		
		for(OrderItem orderItem:orderItemList) {
			totalPrice+=orderItem.getSubTotal();
		}
		totalPrice=totalPrice+getTax();
		return totalPrice;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", status=" + status + ", totalPrice=" + totalPrice
				+ ", orderDate=" + orderDate + ", destinationName=" + destinationName + ", destinationEmail="
				+ destinationEmail + ", destinationZipcode=" + destinationZipcode + ", destinationAddress="
				+ destinationAddress + ", destinationTel=" + destinationTel + ", deliveryTime=" + deliveryTime
				+ ", paymentMethod=" + paymentMethod + ", user=" + user + ", orderItemList=" + orderItemList + "]";
	}
	
	
	
	
}
