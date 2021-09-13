package com.example.controller;
/**
 * ログイン情報のコントローラー.
 * 
 * @author adachiryuji
 *
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login-user")
public class LoginController {
	
	@RequestMapping("")
	public String index() {
		System.out.println("ログインコントローラーを呼べました");
		return "login";
	}
}
