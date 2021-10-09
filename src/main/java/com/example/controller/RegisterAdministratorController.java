package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.AdministratorForm;
import com.example.server.AdministratorService;

/**
 * 管理者情報のコントローラークラス..
 * 
 * @author adachiryuji
 *
 */
@Controller
@RequestMapping("register-administrator")
public class RegisterAdministratorController {
	
	@ModelAttribute
	private Administrator setUpForm() {
		return new Administrator();
	}
	
	@Autowired
	public AdministratorService administratorService;

	@RequestMapping("")
	public String index() {
		return "register_administrator";
	}

	@RequestMapping("register")
	public String register(AdministratorForm form) {
		
		Administrator administrator=new Administrator();
		
		BeanUtils.copyProperties(form,administrator);
		
		administratorService.insert(administrator);
		
		return "redirect:/login-administrator";
	}
	
}
