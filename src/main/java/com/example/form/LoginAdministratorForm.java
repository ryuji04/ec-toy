package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * ログインしている管理者情報のフォーム.
 * 
 * @author adachiryuji
 *
 */
public class LoginAdministratorForm {
	
	/**メールアドレス*/
	@Email(message="Eメール形式が不正です")
	@NotBlank(message="Emailは必須です")
	private String email;
	
	/**パスワード*/
	@NotBlank(message="パスワードは必須です")
	private String password;
	
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
