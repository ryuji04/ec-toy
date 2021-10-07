package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * ログアウトユーザー情報のフォーム.
 * 
 * @author adachiryuji
 *
 */
public class LoginUserForm {
	/**ID*/
	private Integer id;
	/**ユーザー名*/
	private String name;
	/**メールアドレス*/
	@Email(message="Eメール形式が不正です")
	@NotBlank(message="Emailは必須です")
	private String email;
	/**パスワード*/
	@NotBlank(message="パスワードは必須です")
	private String password;
	
	public LoginUserForm() {
	}
	
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
