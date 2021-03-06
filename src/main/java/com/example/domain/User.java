package com.example.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * ユーザーを情報のクラス.
 * 
 * @author adachiryuji
 *
 */
public class User {
	/** 名前 */
	private String name;
	/** メールアドレス */
	private String email;
	/** パスワード */
	private String password;
	/** 郵便番号 */
	private String zipCode;
	/** 住所 */
	private String address;
	/** 電話番号 */
	private String telephone;

	/** 確認用パスワード */
	private String confirmPassword;

	

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

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String toString() {
		return "UserForm [name=" + name + ", email=" + email + ", password=" + password + ", zipCode=" + zipCode
				+ ", address=" + address + ", telephone=" + telephone + ", confirmPassword=" + confirmPassword + "]";
	}

}
