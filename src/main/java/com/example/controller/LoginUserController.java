package com.example.controller;
/**
 * ログイン情報のコントローラー.
 * 
 * @author adachiryuji
 *
 */

import javax.servlet.http.HttpSession;

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
	
	@Autowired
	public HttpSession session;
	
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
		
		session.setAttribute("loginUser",loginUser);
		session.setAttribute("message", loginUser.getName()+"さん,こんにちは!");
		
		
		return "forward:/show-item";
	}
	
	@RequestMapping("logout")
	public String logout() {
		session.removeAttribute("loginUser");
		
		return "login";
	}
}
