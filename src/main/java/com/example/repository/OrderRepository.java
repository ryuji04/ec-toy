package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.domain.Topping;

/**
 * ordersテーブルを操作するリポジトリ.
 * 
 * @author adachiryuji
 *
 */
@Repository
public class OrderRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final ResultSetExtractor<List<Order>> OREDER_ROW_MAPPER = (rs) -> {
		List<Order> orderList = new ArrayList<>();
		List<OrderItem> orderItemList = null;
		List<OrderTopping> orderToppingList = null;
		List<Topping> toppingList = null;
		int idNumber = 0;
		int orderItemNumber = 0;
		while (rs.next()) {
			int nowIdNumber = rs.getInt("id");
			if (idNumber != nowIdNumber) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setUserId(rs.getInt("user_id"));
				order.setStatus(rs.getInt("status"));
				order.setTotalPrice(rs.getInt("total_price"));
				order.setOrderDate(rs.getDate("order_date"));
				order.setDestinationName(rs.getString("destination_name"));
				order.setDestinationEmail(rs.getString("destination_email"));
				order.setDestinationZipcode(rs.getString("destination_zipcode"));
				order.setDestinationAddress(rs.getString("destination_address"));
				order.setDestinationTel(rs.getString("destination_tel"));
				order.setDeliveryTime(rs.getTimestamp("delivery_time"));
				order.setPaymentMethod(rs.getInt("payment_method"));
				orderItemList = new ArrayList<>();
				order.setOrderItemList(orderItemList);
				orderList.add(order);
			}

			if (rs.getInt("oi_id") != 0 && orderItemNumber != rs.getInt("oi_id")) {
				OrderItem orderItem = new OrderItem();
				orderItem.setId(rs.getInt("oi_id"));
				orderItem.setItemId(rs.getInt("oi_item_id"));
				orderItem.setOrderId(rs.getInt("oi_order_id"));
				orderItem.setQuantity(rs.getInt("oi_quantity"));
				String size = rs.getString("oi_size");
				char[] c = size.toCharArray();
				orderItem.setSize(c[0]);
				orderToppingList = new ArrayList<>();
				orderItem.setOrderToppingList(orderToppingList);
				orderItemList.add(orderItem);
				Item item = new Item();
				item.setId(rs.getInt("i_id"));
				item.setName(rs.getString("i_name"));
				item.setDescription(rs.getString("i_description"));
				item.setPrice_m(rs.getInt("i_price_m"));
				item.setPrice_l(rs.getInt("i_price_l"));
				item.setImage_path(rs.getString("i_image_path"));
				item.setDeleted(rs.getBoolean("i_deleted"));
				orderItem.setItem(item);
			}

			if (rs.getInt("ot_id") != 0) {
				OrderTopping orderTopping = new OrderTopping();
				orderTopping.setId(rs.getInt("ot_id"));
				orderTopping.setToppingId(rs.getInt("ot_topping_id"));
				orderTopping.setOrderItemId(rs.getInt("ot_order_item_id"));
				orderToppingList.add(orderTopping);
				Topping topping = new Topping();
				topping.setId(rs.getInt("t_id"));
				topping.setName(rs.getString("t_name"));
				topping.setPrice_m(rs.getInt("t_price_m"));
				topping.setPrice_l(rs.getInt("t_price_l"));
				toppingList = new ArrayList<>();
				toppingList.add(topping);
				orderTopping.setTopping(topping);
			}
			idNumber = nowIdNumber;
			orderItemNumber = rs.getInt("oi_id");

		}
		return orderList;

	};

	public Order insertOrder(Order order) {
		String sql = "INSERT INTO orders(user_id,status,total_price,order_date, destination_name,destination_email,\r\n"
				+ "destination_zipcode,destination_address,destination_tel,delivery_time,payment_method)\r\n"
				+ " VALUES (:userId,:status,:totalPrice,:orderDate,:destinationName,:destinationEmail,:destinationZipcode,:destinationAddress,:destinationTel,"
				+ " :deliveryTime,:paymentMethod);";

		System.out.println("Order" + order);

		SqlParameterSource param = new BeanPropertySqlParameterSource(order);

		KeyHolder keyHolder = new GeneratedKeyHolder();
		String[] keyColmunNames = { "id" };

		template.update(sql, param, keyHolder, keyColmunNames);
		order.setId(keyHolder.getKey().intValue());
		System.out.println(keyHolder.getKey() + "が割り当てられました");
		return order;

	}

	public Order findByUserIdAndStatus(Integer userId, Integer status) {
		String sql = "SELECT o.id, o.user_id, o.status, o.total_price, o.order_date, o.destination_name, o.destination_email, o.destination_zipcode,"
				+ " o.destination_address, o.destination_tel, o.delivery_time, o.payment_method, oi.id oi_id, oi.item_id oi_item_id, oi.order_id oi_order_id,"
				+ " oi.quantity oi_quantity, oi.size oi_size, i.id i_id, i.name i_name, i.description i_description, i.price_m i_price_m, i.price_l i_price_l,"
				+ " i.image_path i_image_path, i.deleted i_deleted,  ot.id ot_id, ot.topping_id ot_topping_id, ot.order_item_id ot_order_item_id, t.id t_id,"
				+ " t.name t_name, t.price_m t_price_m, t.price_l t_price_l FROM orders AS o LEFT OUTER JOIN order_items AS oi ON o.id = oi.order_id"
				+ " LEFT OUTER JOIN items AS i ON oi.item_id = i.id LEFT OUTER JOIN order_toppings AS ot ON oi.id = ot.order_item_id LEFT OUTER JOIN"
				+ " toppings AS t ON t.id = ot.topping_id WHERE user_id = :userId AND status = :status ORDER BY o.id DESC;";

		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("status", status);

		List<Order> orderList = template.query(sql, param, OREDER_ROW_MAPPER);

		if (orderList.size() == 0) {
			return null;
		}

		return orderList.get(0);

	}

	public Order findByUserId(Integer userId) {
		String sql = "SELECT o.id, o.user_id, o.status, o.total_price, o.order_date, o.destination_name,"
				+ "	o.destination_email, o.destination_zipcode, o.destination_address, o.destination_tel, o.delivery_time,"
				+ "	o.payment_method, oi.id oi_id, oi.item_id oi_item_id, oi.order_id oi_order_id, oi.quantity oi_quantity,"
				+ "	oi.size oi_size, i.id i_id, i.name i_name, i.description i_description, i.price_m i_price_m, i.price_l i_price_l,"
				+ "	i.image_path i_image_path, i.deleted i_deleted, ot.id ot_id, ot.topping_id ot_topping_id, "
				+ "	ot.order_item_id ot_order_item_id, t.id t_id, t.name t_name, t.price_m t_price_m, t.price_l t_price_l FROM orders AS o "
				+ "	LEFT OUTER JOIN order_items AS oi ON o.id=oi.order_id LEFT OUTER JOIN items AS i ON oi.item_id=i.id LEFT OUTER JOIN"
				+ "	order_toppings AS ot ON oi.id=ot.order_item_id LEFT OUTER JOIN toppings AS t ON t.id=ot.topping_id "
				+ "	WHERE o.user_id=:userId;";
		System.out.println("userIdの中身:" + userId);
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);

		List<Order> orderList = template.query(sql, param, OREDER_ROW_MAPPER);

		if (orderList.size() == 0) {
			return null;
		}

		return orderList.get(0);

	}
}
