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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.domain.User;
import com.example.form.LoginUserForm;
import com.example.server.LoginUserService;
import com.example.server.UserService;

@Controller
@RequestMapping("login-user")
public class LoginUserController {
	
	@ModelAttribute
	public LoginUserForm setUpForm() {
		return new LoginUserForm();
	}
	
	@Autowired
	public LoginUserService loginUserService;
	
	@Autowired
	public UserService userService;
	
	@Autowired
	public HttpSession session;
	
	@RequestMapping("")
	public String index() {
		return "login";
	}
	
	@RequestMapping("login")
	public String login(@Validated LoginUserForm form,BindingResult result) {
		LoginUser loginUser=loginUserService.findByEmailAndPassword(form.getEmail(),form.getPassword());
		
		
		if(loginUser==null) {
			result.rejectValue("email",null,"メールアドレスまたはパスワードが違います");
		}
		
		if(result.hasErrors()) {
			System.out.println("error:");
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
