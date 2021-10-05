package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.domain.Topping;

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
	
	
	public final static ResultSetExtractor <List<OrderItem>>ORDERITEM_RESULTSETEXTRACTOR
	=(rs)->{
		List<OrderItem>orderItemList=new ArrayList<>();
		List<OrderTopping>orderToppingList=null;
		List<Topping>toppingList=null;
		
		
		int beforeOrderItemId=0;
		int beforOtInOrderItemId=0;
		
		while(rs.next()) {
			int nowOrderItemId=rs.getInt("oi_id");
			
			if(beforeOrderItemId!=nowOrderItemId) 
			{
				OrderItem orderItem=new OrderItem();
				orderItem.setId(rs.getInt("oi_id"));
				orderItem.setItemId(rs.getInt("oi_item_id"));
				orderItem.setOrderId(rs.getInt("oi_order_id"));
				orderItem.setQuantity(rs.getInt("oi_quantity"));
				String c=rs.getString("oi_size");
				char[]size=c.toCharArray();
				for(char size2:size) {
					orderItem.setSize(size2);
				}
				
				Item item=new Item();
				item.setId(rs.getInt("i_id"));
				item.setName(rs.getString("i_name"));
				item.setDescription(rs.getString("i_description"));
				item.setPrice_m(rs.getInt("i_price_m"));
				item.setPrice_l(rs.getInt("i_price_l"));
				item.setImage_path(rs.getString("i_image_path"));
				item.setDeleted(rs.getBoolean("i_deleted"));
				item.setToppingList(toppingList);
				
				orderItem.setItem(item);
				
				orderToppingList=new ArrayList<>();
				orderItem.setOrderToppingList(orderToppingList);
				
				
				
				orderItemList.add(orderItem);
				
			}
			
			if(rs.getInt("ot_id")!=0) {
				if(beforOtInOrderItemId!=rs.getInt("ot_id")) {
					OrderTopping orderTopping=new OrderTopping();
					orderTopping.setId(rs.getInt("ot_id"));
					orderTopping.setToppingId(rs.getInt("ot_topping_id"));
					orderTopping.setOrderItemId(rs.getInt("ot_order_item_id"));
					
					Topping topping=new Topping();
					topping.setId(rs.getInt("t_id"));
					topping.setName(rs.getString("t_name"));
					topping.setPrice_m(rs.getInt("t_price_m"));
					topping.setPrice_l(rs.getInt("t_price_l"));
					
					toppingList=new ArrayList<>();
					toppingList.add(topping);
					
					orderTopping.setTopping(topping);
					orderToppingList.add(orderTopping);
				}
			
		}
					beforeOrderItemId = nowOrderItemId;
					beforOtInOrderItemId = rs.getInt("ot_id");
				}
				return orderItemList;
		
		
	};

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
		String sql
	="SELECT id,item_id,order_id,quantity,size FROM order_items WHERE id=:id";
		
		SqlParameterSource param
		=new MapSqlParameterSource().addValue("id",id);
		
		OrderItem orderItem=template.queryForObject(sql,param,ITEMORDER_ROW_MAPPER);
		
		return orderItem;
		
	}
	
	
	public OrderItem findByIfForHistory(Integer orderItemId) {
		String sql
		="SELECT oi.id oi_id,oi.item_id oi_item_id,oi.order_id oi_order_id,oi.quantity oi_quantity,"
				+ " oi.size oi_size,i.id i_id,i.name i_name,i.description i_description,i.price_m i_price_m,"
				+ " i.price_l i_price_l,i.image_path i_image_path,i.deleted i_deleted,ot.id ot_id,ot.topping_id ot_topping_id,"
				+ " ot.order_item_id ot_order_item_id,t.id t_id,t.name t_name,t.price_m t_price_m,t.price_l t_price_l\r\n"
				+ " FROM order_items AS oi LEFT OUTER JOIN items AS i ON oi.item_id=i.id LEFT OUTER JOIN order_toppings AS ot"
				+ " ON ot.order_item_id=oi.id LEFT OUTER JOIN toppings AS t ON t.id=ot.topping_id"
				+ " WHERE oi.id=:orderItemId order by oi.id desc;";
		
		SqlParameterSource param
		=new MapSqlParameterSource().addValue("orderItemId",orderItemId);
		
		List<OrderItem>orderItemList=template.query(sql,param,ORDERITEM_RESULTSETEXTRACTOR);
		
		if(orderItemList.size()==0) {
			return null;
		}
		
		return orderItemList.get(0);
		
	}
	
	public void update(OrderItem orderItem,Integer orderId) {
		String sql
		="UPDATE order_items SET id=:id,item_id=:itemId,order_id=:orderId,quantity=:quantity,size=:size WHERE order_id = "+orderId+";";
		
		SqlParameterSource param
		=new BeanPropertySqlParameterSource(orderItem);
		
		template.update(sql, param);
	}
	

}
