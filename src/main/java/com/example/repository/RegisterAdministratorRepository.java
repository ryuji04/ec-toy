package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Administrator;

/**
 * 管理者情報のリポジトリクラス.
 * 
 * @author adachiryuji
 *
 */
@Repository
public class RegisterAdministratorRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	public static final RowMapper<Administrator>ADMINISTRATOR_ROW_MAPPER
	=(rs,i)->{
		Administrator administrator=new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setEmail(rs.getString("email"));
		administrator.setPassword(rs.getString("password"));
		
		return administrator;
		
	};
	
	public void insert(Administrator administrator) {
		String sql
		="INSERT INTO administrators (name,email,password) values (:name,:email,:password)";
		
		SqlParameterSource param
		=new BeanPropertySqlParameterSource(administrator);
		
		template.update(sql, param);
		
	}
	
	public Administrator findByEmailAndPassword(String email,String password) {
		String sql
		="SELECT id,name,email,password from administrators where email=:email AND password=:password";
		
		SqlParameterSource param
		=new MapSqlParameterSource().addValue("email", email).addValue("password",password);
		
		List<Administrator>administratorList=template.query(sql, param,ADMINISTRATOR_ROW_MAPPER);
		
		if(administratorList.size()==0) {
			return null;
		}
		
		return administratorList.get(0);
		
	}
	
}
