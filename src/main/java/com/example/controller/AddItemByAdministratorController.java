package com.example.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.domain.Item;
import com.example.form.ItemForm;
import com.example.form.MultipartFileForm;
import com.example.server.ItemService;

/**
 * 管理者が商品を使いするコントローラークラス.
 * 
 * @author adachiryuji
 *
 */
@Controller
@RequestMapping("/add-item-administrator")
public class AddItemByAdministratorController {
	@ModelAttribute
	private Item setUpForm() {
		return new Item();
	}
	
	@Autowired
	private ItemService itemService;

	@RequestMapping("")
	public String index() {
		return "add_item_administrator";
	}

	@RequestMapping("add")
	public String add(ItemForm form,MultipartFile multipartFile, Model model)
			throws Exception {
		if (!multipartFile.isEmpty()) {
			try {
				// ファイル名を社員番号にリネイム
				File oldFileName = new File(multipartFile.getOriginalFilename());
				File newFileName = new File(multipartFile.getOriginalFilename() + ".jpg");
				oldFileName.renameTo(newFileName);
				System.out.print("oldFileName:"+oldFileName);
				// 保存先を定義
				String uploadPath = "src/main/resources/static/img_toy/";
				byte[] bytes = multipartFile.getBytes();

				// 指定ファイルへ読み込みファイルを書き込み
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(uploadPath + newFileName)));
				stream.write(bytes);
				stream.close();
				
				Item item=new Item();
				item.setName(form.getName());
				item.setDescription(form.getDescription());
				item.setPrice_m(form.getPrice_m());
				item.setPrice_l(form.getPrice_l());
				item.setImage_path(oldFileName.getName());
				itemService.insertItem(item);
				
				System.out.println("item:"+item);
				

			} catch (Exception e) {
				System.out.println(e);
			}

		}

		return "forward:/add-item-administrator";

	}
}
