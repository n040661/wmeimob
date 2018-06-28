package com.mm.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 接口doc
 * @author zJun
 * @date 2018年6月26日 下午4:58:09
 */
@Data
public class InterfacDoc {

	/** 请求method */
	private String method;
	/** 请求路径 */
	private String path;
	/** 备注 */
	private String notes;
	/** 提交方式 */
	private Mode mode;
	/** 返回类型 */
	private String resultType;
	/** 返回说明  */
	private String resultNotes;
	/** 作者 */
	private String author;
	/** 时间 */
	private String date;
	/** 参考 */
	private String[] see;
	
	private List<KeyValue> headers;
	private List<KeyValue> params;
	private List<KeyValue> results;
	
	public InterfacDoc() {
		this.mode = Mode.FORM;
	}
	
	public void addHeader(KeyValue kv) {
		if(headers == null) {
			headers = new ArrayList<>();
		}
		headers.add(kv);
	}
	
	public void addParam(KeyValue kv) {
		if(params == null) {
			params = new ArrayList<>();
		}
		params.add(kv);
	}
	
	public void addResult(KeyValue kv) {
		if(results == null) {
			results = new ArrayList<>();
		}
		results.add(kv);
	}
	
	/**
	 * 提交方式
	 * @author zJun
	 * @date 2018年6月28日 下午4:10:28
	 */
	public static enum Mode {
		/** FORM表单提交 */
		FORM,
		/** 提交JSON字符串 */
		JSON
	}
}
