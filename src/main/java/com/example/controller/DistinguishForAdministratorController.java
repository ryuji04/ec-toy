package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理者かユーザーを判断する為のコントローラークラス.
 * 
 * @author adachiryuji
 *
 */
@Controller
@RequestMapping("distinguish-administrator")
public class DistinguishForAdministratorController {
	@RequestMapping("")
	public String index() {
		return "distinguish_administrator";
	}
}
