package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Topping;

/**
 * トッピング情報のリポジトリ.
 * 
 * @author adachiryuji
 *
 */
@Repository
public class ToppingRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Topping> TOPPING_ROW_MAPPER = (rs, i) -> {
		Topping topping = new Topping();
		topping.setId(rs.getInt("id"));
		topping.setName(rs.getString("name"));
		topping.setPrice_m(rs.getInt("price_m"));
		topping.setPrice_l(rs.getInt("price_l"));
		return topping;
	};

	/**
	 * 全てのトッピング情報を取得するメソッドです.
	 * 
	 * @return トッピング情報のリスト
	 */
	public List<Topping> findAllTopping() {
		String sql = "SELECT id,name,price_m,price_l FROM toppings";
		List<Topping> toppingList = template.query(sql, TOPPING_ROW_MAPPER);
		return toppingList;
	}

	public Topping findByToppingId(Integer id) {
		String sql = "SELECT id,name,price_m,price_l FROM toppings WHERE id=:id";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		List<Topping> toppingList = template.query(sql, param, TOPPING_ROW_MAPPER);

		if (toppingList.size() == 0) {
			return null;
		}

		return toppingList.get(0);
	}

}
