package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.domain.LoginUser;
import com.example.form.LoginAdministratorForm;
import com.example.server.AdministratorService;

/**
 * ログイン（管理者）するコントローラークラス.
 * 
 * @author adachiryuji
 *
 */
@Controller
@RequestMapping("/login-administrator")
public class Login_AdministratorController {
	
	@Autowired
	private HttpSession session;

	@ModelAttribute
	private LoginAdministratorForm setUpForm() {
		return new LoginAdministratorForm();
	}

	@Autowired
	private AdministratorService administratorService;

	@RequestMapping("")
	public String index() {
		return "login_administrator";
	}

	@RequestMapping("login")
	public String login(LoginAdministratorForm form) {
		
		
		
		LoginUser loginUser=(LoginUser)(session.getAttribute("loginUser"));
		
		if(loginUser!=null) {
			session.removeAttribute("loginUser");
			return "confirm_administrator";
		}
		

		Administrator administrator = administratorService.findByEmailAndPassword(form.getEmail(), form.password);

		System.out.println("administrator:"+administrator);
		if (administrator == null) {
			
			
			return "forward:/login-administrator";
		}

		return "done";

	}

}
