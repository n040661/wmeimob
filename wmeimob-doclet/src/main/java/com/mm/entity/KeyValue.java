package com.mm.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * KeyValue
 * @author zJun
 * @date 2018年6月26日 下午5:06:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KeyValue {

	private String key;
	private Object value;
	private String notes;
	private boolean required;
	private String type;
	private List<Item> enumItem;
	
	public KeyValue(String key, Object value, String notes, boolean required, String type) {
		this.key = key;
		this.value = value;
		this.notes = notes;
		this.required = required;
		this.type = type;
	}
	
	/**
	 * 添加枚举成员
	 * @param item
	 * @author zJun
	 * @date 2018年6月27日 下午4:35:09
	 */
	public void addItem(Item item) {
		if(enumItem == null) {
			enumItem = new ArrayList<>();
		}
		enumItem.add(item);
	}
	
	/**
	 * 枚举成员
	 * @author zJun
	 * @date 2018年6月27日 下午4:33:55
	 */
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Item {
		/**
		 * 枚举备注
		 */
		private String notes;
		/**
		 * 枚举值
		 */
		private String value;
	}
}
