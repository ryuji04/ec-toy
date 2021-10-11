package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 管理者情報のフォーム.
 * 
 * @author adachiryuji
 *
 */
public class AdministratorForm {
	/**ID*/
	public Integer id;
	/**名前*/
	@NotBlank(message="名前は必須です")
	public String name;
	/**メールアドレス*/
	@NotBlank(message="Emailは必須です")
			@Email(message="Email形式で入力願います")
	public String email;
	/**パスワード*/
	@Pattern(regexp="(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[0-9])[a-zA-Z0-9]{8,16}",message="8文字以上16文字以下で小文字、大文字、数字を含んで記入願います")
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
