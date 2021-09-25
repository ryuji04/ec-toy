package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.domain.OrderItem;

/**
 * 注文する商品のリポジトリ.
 * 
 * @author adachiryuji
 *
 */
@Repository
public class OrderItemRepository {
	@Autowired
	public NamedParameterJdbcTemplate template;

	public final static RowMapper<OrderItem> ITEMORDER_ROW_MAPPER = (rs, i) -> {
		OrderItem orderItem = new OrderItem();
		orderItem.setId(rs.getInt("id"));
		orderItem.setItemId(rs.getInt("item_id"));
		orderItem.setOrderId(rs.getInt("order_id"));
		orderItem.setQuantity(rs.getInt("quantity"));
		String size = rs.getString("size");
		char[] c = size.toCharArray();
		orderItem.setSize(c[0]);
		return orderItem;
	};

	/**
	 * 注文商品情報を挿入する.
	 * 
	 * @param orderItem 注文商品情報
	 * @return 挿入した注文商品
	 */
	public OrderItem insert(OrderItem orderItem) {
		String sql = "INSERT INTO order_items(item_id,order_id,quantity,size) VALUES(:itemId,:orderId,:quantity,:size)";

		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);

		// insertされる時の自動採番IDがセットされるように記述
		KeyHolder keyHolder = new GeneratedKeyHolder();
		String[] keyColmnNames = { "id" };
		template.update(sql, param, keyHolder, keyColmnNames);
		orderItem.setId(keyHolder.getKey().intValue());
		System.out.println(keyHolder.getKey() + "が割り当てられました");
		return orderItem;
	}

}
