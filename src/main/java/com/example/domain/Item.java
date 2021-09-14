package com.example.domain;
/**
 * 商品情報のクラス.
 * 
 * @author adachiryuji
 *
 */
public class Item {
	/** ID */
	private Integer id;
	/** 商品の名前 */
	private String name;
	/** 商品価格(Mサイズ)　*/
	private Integer price_m;
	/**　商品価格(Mサイズ)　 */
	private Integer price_l;
	/**　商品画像のパス */
	private String	image_path;
	
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
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", price_m=" + price_m + ", price_l=" + price_l + ", image_path="
				+ image_path + "]";
	}
	
	
	
	
}
