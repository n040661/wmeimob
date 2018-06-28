package com.mm.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.sun.javadoc.ClassDoc;
import com.wmeimob.util.LogUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 单例、用于暂时储存ClassDoc数据
 * @author zJun
 * @date 2018年6月26日 下午2:42:05
 */
@Slf4j
public class SingletonDocs {

	private volatile static SingletonDocs instance;
	private Map<String, ClassDoc> clsMap = new HashMap<>(16);
	private String contextPath;

	private SingletonDocs() {
		this.contextPath = "";
	}

	public static SingletonDocs getInstance() {
		if (instance == null) {
			synchronized (SingletonDocs.class) {
				if (instance == null) {
					instance = new SingletonDocs();
				}
			}
		}
		return instance;
	}

	public void put(ClassDoc value) {
		String key = value.qualifiedTypeName();
		if(clsMap.get(key) != null) {
			LogUtil.warn(log, "类名{}存在重复", key);
			return;
		}
		clsMap.put(key, value);
	}

	public ClassDoc get(String key) {
		return clsMap.get(key);
	}
	
	public int size() {
		return this.clsMap.size();
	}
	
	public Set<Entry<String, ClassDoc>> entrySet(){
		return clsMap.entrySet();
	}
	
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
	
	public String getContextPath() {
		return contextPath;
	}
}
