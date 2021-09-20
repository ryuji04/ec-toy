package com.example.controller;
/**
 * ログイン情報のコントローラー.
 * 
 * @author adachiryuji
 *
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.form.LoginUserForm;
import com.example.server.LoginUserService;

@Controller
@RequestMapping("login-user")
public class LoginUserController {
	@Autowired
	public LoginUserService loginUserService;
	
	@RequestMapping("")
	public String index() {
		return "login";
	}
	
	@RequestMapping("login")
	public String login(LoginUserForm form) {
		LoginUser loginUser=loginUserService.findByEmailAndPassword(form.getEmail(),form.getPassword());
		if(loginUser==null) {
			return "login";
		}
		
		return "redirect:/show-item";
	}
}
