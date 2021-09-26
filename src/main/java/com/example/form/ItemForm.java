package com.example.form;

import java.util.List;

import com.example.domain.Topping;
/**
 * 商品情報のフォームクラス.
 * 
 * @author adachiryuji
 *
 */
public class ItemForm {
	/** ID */
	private Integer id;
	/** 商品の名前 */
	private String name;
	/** 商品の説明 */
	private String description;
	/** 商品価格(Mサイズ) */
	private Integer price_m;
	/** 商品価格(Mサイズ) */
	private Integer price_l;
	/** 商品画像のパス */
	private String image_path;
	/** トッピング情報を格納する為のリスト */
	private List<Topping> toppingList;
	

	public ItemForm() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPrice_m() {
		return price_m;
	}

	public void setPrice_m(Integer price_m) {
		this.price_m = price_m;
	}

	public Integer getPrice_l() {
		return price_l;
	}

	public void setPrice_l(Integer price_l) {
		this.price_l = price_l;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	public List<Topping> getToppingList() {
		return toppingList;
	}

	public void setToppingList(List<Topping> toppingList) {
		this.toppingList = toppingList;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", description=" + description + ", price_m=" + price_m
				+ ", price_l=" + price_l + ", image_path=" + image_path + ", toppingList=" + toppingList + "]";
	}

}
