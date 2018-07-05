package com.mm.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 单例、用于暂时储存ClassDoc数据
 * @author zJun
 * @date 2018年6月26日 下午2:42:05
 */
public class SingletonModule {

	private volatile static SingletonModule instance;
	public List<ModuleDoc> data = new ArrayList<>(16);
	public Map<String, ModuleDoc> temp = new HashMap<>(16);

	private SingletonModule() {
	}

	public static SingletonModule getInstance() {
		if (instance == null) {
			synchronized (SingletonModule.class) {
				if (instance == null) {
					instance = new SingletonModule();
				}
			}
		}
		return instance;
	}

	/**
	 * put
	 * @param key
	 * @param value
	 * @param isTop 是否是顶层module
	 * @author zJun
	 * @date 2018年6月28日 上午11:07:55
	 */
	public void put(String key, ModuleDoc value, boolean isTop) {
		if(key == null) {
			return;
		}
		if(temp.get(key) == null) {
			this.temp.put(key, value);
			if(isTop) {
				this.data.add(value);
			}
		}
	}

	public ModuleDoc get(String key) {
		return this.temp.get(key);
	}
	
	public int size() {
		return this.temp.size();
	}
	
	public Set<Entry<String, ModuleDoc>> entrySet(){
		return temp.entrySet();
	}
	
	public String datoToJSONString() {
		return JSON.toJSONString(data, SerializerFeature.DisableCircularReferenceDetect); 
	}
}
