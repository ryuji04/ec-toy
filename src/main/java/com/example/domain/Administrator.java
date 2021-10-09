package com.example.domain;
/**
 * 管理者情報のクラス.
 * 
 * @author adachiryuji
 *
 */
public class Administrator {
	/**ID*/
	public Integer id;
	/**名前*/
	public String name;
	/**メールアドレス*/
	public String email;
	/**パスワード*/
	public String password;
	/**確認用パスワード*/
	public String conifirmPassword;
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
	public String getConifirmPassword() {
		return conifirmPassword;
	}
	public void setConifirmPassword(String conifirmPassword) {
		this.conifirmPassword = conifirmPassword;
	}
	@Override
	public String toString() {
		return "Administrators [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", conifirmPassword=" + conifirmPassword + "]";
	}
	
	
}
