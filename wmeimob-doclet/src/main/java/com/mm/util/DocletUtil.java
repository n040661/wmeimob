package com.mm.util;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import com.alibaba.fastjson.JSONArray;
import com.mm.doclet.IApiConstant;
import com.mm.entity.InterfacDoc;
import com.mm.entity.KeyValue;
import com.mm.entity.SingletonDocs;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.ParameterizedType;
import com.sun.javadoc.Tag;

public class DocletUtil {

	/**
	 * 解析"@param"Tag
	 * @param tag
	 * @param cMap 选中属性map
	 * @param eMap 排除属性map
	 * @return
	 * @author zJun
	 * @date 2018年6月27日 上午11:17:13
	 */
	public static void eachParamTag(InterfacDoc iDoc, Tag tag, String type, Map<String, Boolean> cMap, Map<String, Boolean> eMap) {
		String text = tag.text();
		StringTokenizer strToken = new StringTokenizer(text, " ");
		String key = null;
		String value = null;
		String notes = null;
		boolean required = false;
		
		if(strToken.hasMoreTokens()) {
			key = strToken.nextToken();
		}
		if(strToken.hasMoreTokens()) {
			value = strToken.nextToken();
		}
		if(strToken.hasMoreTokens()) {
			notes = strToken.nextToken();
		}
		if(strToken.hasMoreTokens()) {
			required = Boolean.parseBoolean(strToken.nextToken());
		}
//		if(strToken.hasMoreTokens()) {
//			type = strToken.nextToken();
//		}
		
		if(IApiConstant.NA.equals(value)) {
			value = null;
		}
		
		ClassDoc typeDoc = SingletonDocs.getInstance().get(type);
		if(typeDoc != null) {
			KeyValue kv = null;
			if(typeDoc.isEnum()) {
				kv = new KeyValue(key, value, notes, required, IApiConstant.TYPE_ENUM);
				exeEnum(kv, typeDoc);
				iDoc.addParam(kv);
			}
			JSONArray ary = eachField(typeDoc, cMap, eMap, null);
			for(int i = 0; i < ary.size(); i++) {
				kv = ary.getObject(i, KeyValue.class);
				iDoc.addParam(kv);
			}
		} else {
			iDoc.addParam(new KeyValue(key, value, notes, required, type));
		}
	}
	
	/**
	 * 解析"@header"Tag
	 * @param tags
	 * @return
	 * @author zJun
	 * @date 2018年6月27日 上午10:23:35
	 */
	public static Map<String, String> getHeaderTag(Tag[] tags) {
		Map<String, String> map = new HashMap<>();
		if (tags != null) {
			for (Tag tag : tags) {
				StringTokenizer sToken = new StringTokenizer(tag.text(), " ");
				String key = null;
				String value = null;
				if (sToken.hasMoreElements()) {
					key = sToken.nextToken();
				}
				if (sToken.hasMoreElements()) {
					value = sToken.nextToken();
				}
				map.put(key, value);
			}
		}
		return map;
	}
	
	/**
	 * 解析类属性field
	 * @param doc
	 * @param cMap
	 * @param eMap
	 * @param typeDoc 泛型类
	 * @return
	 * @author zJun
	 * @date 2018年6月28日 下午6:05:09
	 */
	public static JSONArray eachField(ClassDoc doc, Map<String, Boolean> cMap, Map<String, Boolean> eMap, ClassDoc typeDoc) {
		FieldDoc[] fDocs = doc.fields();
		JSONArray ary = new JSONArray();
		superField(doc.superclass(), ary, cMap, eMap, typeDoc);
		exeFieldDocs(fDocs, ary, cMap, eMap, typeDoc);
		return ary;
	}
	
	/**
	 * 解析父类的属性
	 * @param superDoc
	 * @param ary
	 * @author zJun
	 * @date 2018年6月27日 上午10:46:57
	 */
	private static void superField(ClassDoc superDoc, JSONArray ary, Map<String, Boolean> cMap, Map<String, Boolean> eMap, ClassDoc typeDoc) {
		FieldDoc[] fDocs = superDoc.fields();
		exeFieldDocs(fDocs, ary, cMap, eMap, typeDoc);
	}
	
	/**
	 * 解析类属性
	 * @param fDocs
	 * @param ary
	 * @author zJun
	 * @date 2018年6月27日 上午10:47:25
	 */
	private static void exeFieldDocs(FieldDoc[] fDocs, JSONArray ary, Map<String, Boolean> cMap, Map<String, Boolean> eMap, ClassDoc typeDoc) {
		if(fDocs == null) {
			return;
		}
		for(FieldDoc fDoc : fDocs) {
			if(fDoc.isStatic() || fDoc.isFinal()) {
				continue;
			}
			String key = fDoc.name();
			if(cMap != null) {
				if(cMap.get(key) == null) {
					continue;
				}
			} else if(eMap != null) {
				if(eMap.get(key) != null) {
					continue;
				}
			}
			
			ParameterizedType pt = fDoc.type().asParameterizedType();
			String type = fDoc.type().simpleTypeName();
			
			ClassDoc docT = SingletonDocs.getInstance().get(type);
			Object def = null;
			if(docT != null) {
				// 枚举类型
				if(docT.isEnum()) {
					def = getDefault(fDoc);
					KeyValue kv = new KeyValue(fDoc.name(), def, fDoc.commentText(), false, IApiConstant.TYPE_ENUM);
					exeEnum(kv, docT);
					ary.add(kv);
					continue;
				} else {
					def = eachField(docT, cMap, eMap, typeDoc);
					type = IApiConstant.TYPE_OBJECT;
				}
			} else if(pt != null) {
				docT = SingletonDocs.getInstance().get(pt.typeArguments()[0].qualifiedTypeName());
				if(docT == null) {
					docT = typeDoc;
				}
				def = eachField(docT, cMap, eMap, typeDoc);
				type = IApiConstant.TYPE_ARRAY;
			} else {
				def = getDefault(fDoc);
			}
			
			ary.add(new KeyValue(key, def, fDoc.commentText(), false, type));
		}
	}
	
	/**
	 * 处理枚举类型
	 * @param kv
	 * @param docT
	 * @author zJun
	 * @date 2018年6月27日 下午5:02:07
	 */
	private static void exeEnum(KeyValue kv, ClassDoc docT) {
		FieldDoc[] fDocs = docT.enumConstants();
		for(FieldDoc fd : fDocs) {
			kv.addItem(new KeyValue.Item(fd.commentText(), fd.name()));
		}
	}

	
	
	private static Object getDefault(FieldDoc fDoc) {
		Tag[] ts = fDoc.tags(IApiConstant.DOC_DEFAULT);
		if(ts.length > 0) {
			return ts[0].text();
		}
		return null;
	}
}
