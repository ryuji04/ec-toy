package com.example.form;
/**
 * 商品並び替えに関するフォーム.
 * 
 * @author adachiryuji
 *
 */
public class SortItemForm {
	private Integer sortItem;

	public Integer getSortItem() {
		return sortItem;
	}

	public void setSortItem(Integer sortItem) {
		this.sortItem = sortItem;
	}

	@Override
	public String toString() {
		return "SortItemForm [sortItem=" + sortItem + "]";
	}
	
	
}
