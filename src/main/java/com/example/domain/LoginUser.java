package com.example.domain;
/**
 * ログインユーザー情報のクラス.
 * 
 * @author adachiryuji
 *
 */
public class LoginUser {
	/**ID*/
	private Integer id;
	/**ユーザー名*/
	private String name;
	/**メールアドレス*/
	private String email;
	/**パスワード*/
	private String password;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginUser [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}
	
	
	

}
