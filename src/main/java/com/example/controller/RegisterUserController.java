package com.example.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@RequestMapping("/register-user")
public class RegisterUserController {
	
	@ModelAttribute
	public UserForm setUpForm() {
		return  new UserForm(); 
	}
	
	@Autowired
	public RegisterUserService registerUserService;

	@RequestMapping("")
	public String index() {
		return "register_user";
	}

	@RequestMapping("register")
	public String register (@Validated UserForm form,BindingResult result) {
		
		
		List<User>userList= registerUserService.findAll();
		
		for(User user:userList) {
			if(user.getEmail().equals(form.getEmail())) {
				result.rejectValue("email", null,"既に使われているメールアドレスです");
			}
		}
		
		if(!form.getPassword().equals(form.getConfirmPassword())) {
			result.rejectValue("password", null,"パスワードと確認用パスワードが異なります。");
			result.rejectValue("confirmPassword", null,"パスワードと確認用パスワードが異なります。");
		}
		
		if(result.hasErrors()) {
			return "register_user";
		}
		
		
		
		User user = new User();
		BeanUtils.copyProperties(form, user);
		user.setPassword(form.getPassword());
		
		registerUserService.registerUser(user);
		return "redirect:/login-user";

	}
}
