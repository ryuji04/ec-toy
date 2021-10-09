package com.example.form;
/**
 * ログインしている管理者情報のフォーム.
 * 
 * @author adachiryuji
 *
 */
public class LoginAdministratorForm {
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
