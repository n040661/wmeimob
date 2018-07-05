package com.mm;


import com.mm.doclet.ControllerDoclet;
import com.mm.doclet.Main;
import com.mm.entity.SingletonDocs;
import com.mm.entity.SingletonModule;

public class Doclet {

	public static void main(String[] args) {
		SingletonDocs instance = SingletonDocs.getInstance();
		// 全局的上下文contextPath配置
		instance.setContextPath("/doclet-api");
		// 您要加载的包路径
		String subpackages = "com.mm";
		// 项目路径；您java代码的路径
		String path = "E:\\word\\2018.5.8\\wmeimob\\wmeimob-doclet-example\\";
		// 加载代码，可多次加载。也就是说能加载多个目录的代码，maven分模块会用到
		// Main.go(path, subpackages);
		path = "E:\\word\\2018.5.8\\wmeimob\\wmeimob-doclet-example\\";
		Main.go(path, subpackages);
		// 开始解析生成JSON
		ControllerDoclet.execute();
		// 输出JSON到控制台、怎么用您自己决定
		System.out.println(SingletonModule.getInstance().datoToJSONString());
	}

}
