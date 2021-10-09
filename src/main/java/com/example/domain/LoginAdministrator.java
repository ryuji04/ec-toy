package com.example.domain;
/**
 * ログインしている管理者情報のクラス.
 * 
 * @author adachiryuji
 *
 */
public class LoginAdministrator {
	/**メールアドレス*/
	public String email;
	/**パスワード*/
	public String password;
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
		return "LoginAdministrator [email=" + email + ", password=" + password + "]";
	}
	
	
}
