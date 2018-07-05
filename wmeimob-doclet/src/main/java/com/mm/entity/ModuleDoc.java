package com.mm.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 模块
 * @author zJun
 * @date 2018年6月26日 下午4:55:00
 */
@Data
public class ModuleDoc {

	private String id;
	private String title;
	private boolean expand;
	private List<Object> children;
//	private List<InterfacDoc> docs;
	
	public ModuleDoc() {
		this.expand = true;
	}
	
	public ModuleDoc(String id, String title) {
		this.id = id;
		this.title = title;
		this.expand = true;
	}
	
	/**
	 * 添加成员
	 * @param item
	 * @author zJun
	 * @date 2018年6月26日 下午7:35:22
	 */
	public void add(ModuleDoc item) {
		if(children == null) {
			children = new ArrayList<>();
		}
		if(!children.contains(item)) {
			children.add(item);
		}
	}
	
	/**
	 * 添加接口Doc成员
	 * @param item
	 * @author zJun
	 * @date 2018年6月27日 上午11:36:35
	 */
	public void addInterfacDoc(InterfacDoc item) {
		if(children == null) {
			children = new ArrayList<>();
		}
		children.add(item);
	}
	
}
