package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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
		return orderItem;
	}
	
	/**
	 * 注文する商品の情報を削除する.
	 * 
	 * @param id ID
	 */
	public void deleteById(Integer id) {
		String sql
		="DELETE FROM order_items WHERE id=:id";
		
		SqlParameterSource param
		=new MapSqlParameterSource().addValue("id",id);
		
		template.update(sql, param);
	}
	
	/**
	 * IDから注文する商品の情報を検索する.
	 * 
	 * @param id
	 * @return 注文する商品の情報
	 */
	public OrderItem findById(Integer id) {
		System.out.println("id:"+id);
		String sql
	="SELECT id,item_id,order_id,quantity,size FROM order_items WHERE id=:id";
		
		SqlParameterSource param
		=new MapSqlParameterSource().addValue("id",id);
		
		OrderItem orderItem=template.queryForObject(sql,param,ITEMORDER_ROW_MAPPER);
		
		return orderItem;
		
	}

}
