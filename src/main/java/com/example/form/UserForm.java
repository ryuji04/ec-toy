package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UserForm {
	/** 名前　*/
	@NotBlank(message="名前の入力は必須です")
	private String name;
	/**　メールアドレス */
	@Email(message="Email形式で入力をお願いします")
	@NotBlank(message="Emailの入力は必須です")
	private String email;
	/**　パスワード */
	@NotBlank(message="パスワードの入力は必須です")
	@Pattern(regexp="(?=.*?[a-z])(?=.*?[A-Z])(?=.*?\\d)(?=.*?[+-/_])[a-zA-Z\\d+-/_]{8,16}",message="8文字以上16文字以内で記入願います")
	private String password;
	/** 郵便番号　*/
	@NotBlank(message="郵便番号の入力は必須です")
	@Pattern(regexp="^[0-9]{3}-[0-9]{4}$",message="XXX-XXXXの形式で入力願います")
	private String zipCode;
	/**　住所 */
	@NotBlank(message="住所の入力は必須です")
	private String address;
	/** 電話番号　*/
	@NotBlank(message="電話番号の入力は必須です")
	@Pattern(regexp="^[0-9]{3}-[0-9]{4}-[0-9]{4}$",message="XXX-XXXX-XXXXの形式で入力願います")
	private String telephone;
	
	/**確認用パスワード*/
	private String confirmPassword;
	
	
	public UserForm() {
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
