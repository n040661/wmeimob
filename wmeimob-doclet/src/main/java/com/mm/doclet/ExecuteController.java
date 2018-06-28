package com.mm.doclet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.mm.entity.KeyValue;
import com.mm.entity.ModuleDoc;
import com.mm.entity.RequestMapping;
import com.mm.entity.SingletonModule;
import com.mm.util.DocletUtil;
import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.ClassDoc;

/**
 * 解析Controller
 * @author zJun
 * @date 2018年6月26日 下午7:51:39
 */
public class ExecuteController {

	private ClassDoc cd;
	private ModuleDoc pModule;

	public ExecuteController(ClassDoc cd) {
		this.cd = cd;
		execute();
	}
	
	/**
	 * 检测是否有@RestController注解后开始执行
	 * @author zJun
	 * @date 2018年6月26日 下午5:39:23
	 */
	private void execute() {
		RequestMapping rm = null;
		// 查找RequestMapping注解
		AnnotationDesc[] ads = cd.annotations();
		for (AnnotationDesc ad : ads) {
			rm = RequestMapping.build(ad);
			if (rm != null) {
				break;
			}
		}

		if (rm != null) {
			start(rm);
		}
	}
	
	/**
	 * 初始化module
	 * @param key
	 * @param title
	 * @param isTop 是否顶层module
	 * @author zJun
	 * @date 2018年6月28日 上午11:08:44
	 */
	private void module(String key, String title, boolean isTop) {
		SingletonModule modules = SingletonModule.getInstance();
		ModuleDoc module = modules.get(key);
		if (module == null) {
			module = new ModuleDoc(title);
			modules.put(key, module, isTop);
		}
		if (pModule != null) {
			pModule.add(module);
		}
		pModule = module;
	}
	
	private void start(RequestMapping rm) {
		String path = rm.getName();
		if (path == null) {
			path = "/";
		}
		StringTokenizer sToken = new StringTokenizer(path.replaceFirst("/", ""), "/");
		String key = "", title = null;
		if(sToken.hasMoreElements()) {
			title = sToken.nextToken();
			key += title + "_";
			module(key, title, true);
		} else {
			module(key, title, true);
		}
		
		while (sToken.hasMoreElements()) {
			title = sToken.nextToken();
			key += title + "_";
			module(key, title, false);
		}
		
		List<KeyValue> list = headers(rm);
		new ExecuteControllerFun(cd, pModule, path, list);
	}
	
	private List<KeyValue> headers(RequestMapping rm) {
		List<KeyValue> list = new ArrayList<>();
		Map<String, String> map = DocletUtil.getHeaderTag(cd.tags(IApiConstant.DOC_HEADER));
		if (rm != null) {
			String[] headers = rm.getHeaders();
			if (headers != null) {
				for (String key : headers) {
					list.add(new KeyValue(key, null, map.get(key), true, IApiConstant.TYPE_STRING));
				}
			}

		}
		return list;
	}
}
