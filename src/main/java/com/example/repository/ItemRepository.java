package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Item;

/**
 * 商品情報のリポジトリ.
 * 
 * @author adachiryuji
 *
 */
@Repository

public class ItemRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setName(rs.getString("name"));
		item.setPrice_m(rs.getInt("price_m"));
		item.setPrice_l(rs.getInt("price_l"));
		item.setImage_path(rs.getString("image_path"));
		return item;
	};

	/**
	 * 商品状を全件検索します.
	 * 
	 * @return 全ての商品情報
	 */
	public List<Item> findAll() {
		String sql = "SELECT id,name,price_m,price_l,image_path FROM items";

		List<Item> itemList = template.query(sql, ITEM_ROW_MAPPER);
		return itemList;

	}
	
	public List<Item>findLikeName(String name){
		String sql = "SELECT id,name,price_m,price_l,image_path FROM items WHERE name LIKE :name";
		
		SqlParameterSource param
		=new MapSqlParameterSource().addValue("name","%"+name+"%");
		
		List<Item>itemList=template.query(sql, param,ITEM_ROW_MAPPER);
		
		return itemList;
		
	}

}
