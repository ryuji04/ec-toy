package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.UserForm;
import com.example.server.RegisterUserService;

/**
 * ユーザー情報を登録するコントローラー.
 * 
 * @author adachiryuji
 *
 */
@Controller
@RequestMapping("register-user")
public class RegisterUserController {
	@Autowired
	public RegisterUserService registerUserService;

	@RequestMapping("")
	public String index() {
		return "register_user";
	}

	@RequestMapping("register")
	public String regist(UserForm form) {
		User user = new User();
		BeanUtils.copyProperties(form, user);
		user.setPassword(form.getPassword());
		registerUserService.registerUser(user);
		return "redirect:/login-user";

	}
}