package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("ex15")
public class ex15 {
	@RequestMapping("")
	public String index(Model model) {
		// チームリスト
		List<List<String>> teamList = new ArrayList<>();
		
		// タイトルリスト
		List<String> titleList = new ArrayList<>();
		titleList.add("チーム名");
		titleList.add("先鋒");
		titleList.add("中堅");
		titleList.add("大将");
		teamList.add(titleList);
		
		// Aチームメンバーリスト
		List<String> memberAList = new ArrayList<>();
		memberAList.add("Aチーム");
		memberAList.add("伊藤");
		memberAList.add("佐藤");
		memberAList.add("齋藤");
		teamList.add(memberAList);
		
		// Bチームメンバーリスト
		List<String> memberBList = new ArrayList<>();
		memberBList.add("Bチーム");
		memberBList.add("宇賀");
		memberBList.add("伊賀");
		memberBList.add("大賀");
		teamList.add(memberBList);
		
		// Cチームメンバーリスト
		List<String> memberCList = new ArrayList<>();
		memberCList.add("Cチーム");
		memberCList.add("池田");
		memberCList.add("糸田");
		memberCList.add("山田");
		teamList.add(memberCList);
		
		System.out.println("teamListの中身"+teamList);
		
		// リクエストスコープにセット
		model.addAttribute("teamList", teamList);
		
		return "ex15";
	}

}
