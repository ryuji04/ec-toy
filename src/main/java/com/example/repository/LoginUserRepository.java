package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.LoginUser;

/**
 * ログインユーザー情報のリポジトリ.
 * 
 * @author adachiryuji
 */
@Repository
public class LoginUserRepository {
	@Autowired
	public NamedParameterJdbcTemplate template;
	
	private final static RowMapper<LoginUser>LOGINUSER_ROW_MAPPER
	=(rs,i)->{
		LoginUser loginUser=new LoginUser();
		loginUser.setId(rs.getInt("id"));
		loginUser.setName(rs.getString("name"));
		loginUser.setEmail(rs.getString("email"));
		loginUser.setPassword(rs.getString("password"));
		
		return loginUser;
	};
	
	public LoginUser findByEmailAndPassword(String email,String password) {
		String Sql
		="SELECT id,name,email,password FROM users WHERE email=:email AND password=:password";
		
		SqlParameterSource param
		=new MapSqlParameterSource().addValue("email",email).addValue("password",password);		
		List<LoginUser>loginUserList=template.query(Sql,param,LOGINUSER_ROW_MAPPER);
		
		if(loginUserList.size()==0) {
			return null;
		}
		
		return loginUserList.get(0);
	}
}
