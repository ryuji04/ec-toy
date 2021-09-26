package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.OrderTopping;

/**
 * 注文するトッピング情報のリポジトリクラス.
 * 
 * @author adachiryuji
 *
 */
@Repository
public class OrderToppingRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * トッピング情報を挿入する.
	 * 
	 * @param orderTopping トッピング情報
	 */
	public void insertOrderTopping(OrderTopping orderTopping) {
		String sql
		="INSERT INTO order_toppings (topping_id,order_item_id) VALUES(:toppingId,:orderItemId)";
	
		SqlParameterSource param
		=new BeanPropertySqlParameterSource(orderTopping);
		
		template.update(sql, param);
	}
	
	/**
	 * トッピング情報を削除する.
	 * 
	 * @param orderItemId 注文する商品情報のID
	 */
	public void deleteByOrderItemId(Integer orderItemId) {
		String sql = "DELETE FROM order_toppings WHERE order_item_id=:order_item_id";

		SqlParameterSource param = new MapSqlParameterSource().addValue("order_item_id", orderItemId);

		 template.update(sql, param);

	}
	
}
