package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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
	
	public void insertOrderTopping(OrderTopping orderTopping) {
		String sql
		="INSERT INTO order_toppings (topping_id,order_item_id) VALUES(:toppingId,:orderItemId)";
	
		SqlParameterSource param
		=new BeanPropertySqlParameterSource(orderTopping);
		
		template.update(sql, param);
	}
	
}
