package com.mm.doclet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.alibaba.fastjson.JSONArray;
import com.mm.entity.InterfacDoc;
import com.mm.entity.InterfacDoc.Mode;
import com.mm.entity.KeyValue;
import com.mm.entity.ModuleDoc;
import com.mm.entity.RequestMapping;
import com.mm.entity.SingletonDocs;
import com.mm.util.DocletUtil;
import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.Parameter;
import com.sun.javadoc.ParameterizedType;
import com.sun.javadoc.Tag;
import com.sun.javadoc.Type;
import com.wmeimob.exception.SeriousException;

/**
 * 解析Controller中的函数
 * @author zJun
 * @date 2018年6月26日 下午7:51:28
 */
public class ExecuteControllerFun {

	private ClassDoc cd;
	private ModuleDoc pModule;
	private String path;
	private List<KeyValue> headers;

	public ExecuteControllerFun(ClassDoc cd, ModuleDoc pModule, String path, List<KeyValue> headers) {
		this.cd = cd;
		this.pModule = pModule;
		this.path = path;
		this.headers = headers;
		execute();
	}

	public void execute() {
		MethodDoc[] mDocs = cd.methods();
		InterfacDoc doc = null;
		for (MethodDoc mDoc : mDocs) {
			doc = new InterfacDoc();
			AnnotationDesc[] ads = mDoc.annotations();
			RequestMapping rm = null;
			// 查找RequestMapping注解
			for (AnnotationDesc ad : ads) {
				rm = RequestMapping.build(ad);
				if(rm != null) {
					break;
				}
			}
			
			headers(mDoc, doc, rm);
			param(mDoc, doc, rm);
			result(mDoc, doc);
			authorTag(mDoc, doc);
			dateTag(mDoc, doc);
			seeTag(mDoc, doc);
			pModule.addInterfacDoc(doc);
		}
	}
	
	private void headers(MethodDoc mDoc, InterfacDoc doc, RequestMapping rm) {
		for(KeyValue kv : headers) {
			doc.addHeader(kv);
		}
		if(rm != null) {
			Map<String, String> map = DocletUtil.getHeaderTag(mDoc.tags(IApiConstant.DOC_HEADER));
			String[] headers = rm.getHeaders();
			if(headers != null) {
				for(String key : headers) {
					doc.addHeader(new KeyValue(key, null, map.get(key), true, IApiConstant.TYPE_STRING));
				}
			}
		}
		
	}
	
	private void param(MethodDoc mDoc, InterfacDoc doc, RequestMapping rm) {
		SingletonDocs instance = SingletonDocs.getInstance();
		if(rm != null) {
			String src = instance.getContextPath() + path +"/" + rm.getName();
			doc.setMethod(rm.getMethod());
			doc.setPath(src.replaceAll("//", "/"));
		}
		doc.setNotes(mDoc.commentText());
		
		// 获取选中和排除tag
		Map<String, Boolean> cMap = tagReturnStrs(mDoc, IApiConstant.DOC_CHOICE);
		Map<String, Boolean> eMap = tagReturnStrs(mDoc, IApiConstant.DOC_EXCLUDE);
		Tag[] tags = mDoc.tags(IApiConstant.DOC_PARAM);
		if(tags == null) {
			return;
		}
		int i = 0;
		Parameter[] params = mDoc.parameters();
		for(Tag tag : tags) {
			if(i >= params.length) {
				throw SeriousException.getInstance(String.format("%s()@param标签与接口参数不一致", mDoc.qualifiedName()));
			}
			Parameter p = params[i];
			AnnotationDesc[] ads = p.annotations();
			for (AnnotationDesc ad : ads) {
				if(IApiConstant.REQUEST_BODY.equals(ad.annotationType().simpleTypeName())) {
					doc.setMode(Mode.JSON);
					doc.addHeader(new KeyValue("Content-Type", "application/json", "提交JSON必需", true, IApiConstant.TYPE_STRING));
					break;
				}
			}
			DocletUtil.eachParamTag(doc, tag, p.typeName(), cMap, eMap);
			i++;
		}
	}
	
	private void result(MethodDoc mDoc, InterfacDoc doc) {
		SingletonDocs sDocs = SingletonDocs.getInstance();
		String typeName = mDoc.returnType().simpleTypeName();
		ClassDoc cd = sDocs.get(typeName);
		ClassDoc typeDoc = null;
		ParameterizedType pt = mDoc.returnType().asParameterizedType();
		if(cd == null && pt!= null) {
			cd = SingletonDocs.getInstance().get(pt.typeArguments()[0].simpleTypeName());
		}
		
		if(pt != null) {
			Type[] ts = pt.typeArguments();
			for(Type t : ts) {
				typeDoc = t.asClassDoc();
			}
		}
		
		if (cd != null) {
			JSONArray ary = DocletUtil.eachField(cd, null, null, typeDoc);
			for(int i = 0; i < ary.size(); i++) {
				doc.addResult(ary.getObject(i, KeyValue.class));
			}
		}
		doc.setResultType(typeName);
		Tag[] tags = mDoc.tags(IApiConstant.DOC_RETURN);
		for(Tag tag : tags) {
			doc.setResultNotes(tag.text());
		}
		
	}
	
	/**
	 * author标签处理
	 * @param mDoc
	 * @param doc
	 * @author zJun
	 * @date 2018年6月28日 下午6:35:57
	 */
	private void authorTag(MethodDoc mDoc, InterfacDoc doc) {
		Tag[] tags = mDoc.tags(IApiConstant.DOC_AUTHOR);
		for(Tag tag : tags) {
			doc.setAuthor(tag.text());
		}
	}
	
	private void dateTag(MethodDoc mDoc, InterfacDoc doc) {
		Tag[] tags = mDoc.tags(IApiConstant.DOC_DATE);
		for(Tag tag : tags) {
			doc.setDate(tag.text());
		}
	}
	
	private void seeTag(MethodDoc mDoc, InterfacDoc doc) {
		Tag[] tags = mDoc.tags(IApiConstant.DOC_SEE);
		String[] sees = new String[tags.length];
		int i = 0;
 		for(Tag tag : tags) {
 			sees[i] = tag.text();
 			i++;
 		}
 		doc.setSee(sees);
	}
	
	/**
	 * 返回Tag text 分割后的Map
	 * @param mDoc
	 * @param tagName
	 * @return
	 * @author zJun
	 * @date 2018年6月27日 上午11:14:29
	 */
	private Map<String, Boolean> tagReturnStrs(MethodDoc mDoc, String tagName) {
		Tag[] tags = mDoc.tags(tagName);
		if(tags == null || tags.length == 0) {
			return null;
		}
		Map<String, Boolean> map = new HashMap<>();
		for(Tag tag : tags) {
			StringTokenizer st = new StringTokenizer(tag.text(), " ");
			while (st.hasMoreElements()) {
				map.put(st.nextToken(), true);
			}
		}
		return map;
	}
}
