package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
	private AdministratorForm setUpForm() {
		return new AdministratorForm();
	}
	
	@Autowired
	public AdministratorService administratorService;

	@RequestMapping("")
	public String index() {
		return "register_administrator";
	}

	@RequestMapping("register")
	public String register(@Validated AdministratorForm form,BindingResult result) {
		
		
		if(result.hasErrors()) {
			return "register_administrator";
		}
		
		Administrator administrator=new Administrator();
		
		BeanUtils.copyProperties(form,administrator);
		
		administratorService.insert(administrator);
		
		return "redirect:/login-administrator";
	}
	
}
