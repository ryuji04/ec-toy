package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
public class LoginAdministratorController {
	
	@ModelAttribute
	private LoginAdministratorForm setUpForm() {
		return new LoginAdministratorForm();
	}
	
	@Autowired
	private HttpSession session;


	@Autowired
	private AdministratorService administratorService;

	@RequestMapping("")
	public String index() {
		return "login_administrator";
	}

	@RequestMapping("login")
	public String login(@Validated LoginAdministratorForm form,BindingResult result) {
		
		Administrator administrator = administratorService.findByEmailAndPassword(form.getEmail(), form.getPassword());
		
		if (administrator == null) {
			result.rejectValue("email", null,"Emailまたはパスワードが異なります");
		}
		
		
		if(result.hasErrors()) {
			return "login_administrator";
		}
		
		LoginUser loginUser=(LoginUser)(session.getAttribute("loginUser"));
		
		if(loginUser!=null) {
			session.removeAttribute("loginUser");
			return "distinguish_administrator";
		}
		

		


		return "select_administrator";

	}

}
