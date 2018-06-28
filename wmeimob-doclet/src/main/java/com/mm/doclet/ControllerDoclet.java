package com.mm.doclet;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.mm.entity.SingletonDocs;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.Doclet;
import com.sun.javadoc.LanguageVersion;
import com.sun.javadoc.RootDoc;
import com.wmeimob.util.LogUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * 获取javadoc注释
 * @author zJun
 * @date 2018年6月26日 上午9:50:57
 */
@Slf4j
public class ControllerDoclet extends Doclet {

	/**
	 * 开始执行
	 * 加载ClassDoc到内存用于之后解析
	 * @param root
	 * @return
	 * @author zJun
	 * @date 2018年6月26日 下午4:24:41
	 */
	public static boolean start(RootDoc root) {
		ClassDoc[] classes = root.classes();
		SingletonDocs instance = SingletonDocs.getInstance();
		for (ClassDoc cls : classes) {
			instance.put(cls);
			LogUtil.info(log, "添加ClassDoc: {}", cls.qualifiedTypeName());
		}
		LogUtil.info(log, "添加ClassDoc数量: {}", classes.length);
		return true ;
	}
	
	public static int optionLength(String option) {
		Map<String, Integer> options = new HashMap<String, Integer>();
		options.put("-outFile", 2);
		options.put("-showMessage", 1);

		Integer value = options.get(option);
		if (value != null) {
			return value;
		} else {
			return 0;
		}
	}

	public static LanguageVersion languageVersion() {
		return LanguageVersion.JAVA_1_5;
	}
	
	/**
	 * 启动程序
	 * @param sourcepath
	 * @param subpackages
	 * @author zJun
	 * @date 2018年6月26日 下午3:33:47
	 */
	public static void start(String sourcepath, String subpackages) {
		String[] docArgs = new String[] { "-doclet", ControllerDoclet.class.getName(), "-private", "-sourcepath", sourcepath,
				"-subpackages", subpackages};
		com.sun.tools.javadoc.Main.execute(docArgs);
	}
	
	/**
	 * 解析加载好的ClassesDoc
	 * @author zJun
	 * @date 2018年6月26日 下午5:18:30
	 */
	public static void execute() {
		SingletonDocs instance = SingletonDocs.getInstance();
		for(Entry<String, ClassDoc> entry : instance.entrySet()) {
			ClassDoc cd = entry.getValue();
			new ExecuteController(cd);
		}
	}

//	public static void main(String[] args) {
//		System.out.println(System.getProperty("user.dir"));
		// String[] docArgs = new String[] { "-doclet",
		// ControllerDoclet.class.getName(),
		// "E:/word/2018.5.8/sass/java/sass-admin-life/src/main/java/"
		// + "com/mm/life/controller/AdminUserController.java"};

//		String[] docArgs = new String[] { "-doclet", ControllerDoclet.class.getName(), "-sourcepath",
//				"E:/word/2018.5.8/sass/java/sass-admin-life/src/main/java/", "-subpackages", "com.mm" };
//		com.sun.tools.javadoc.Main.execute(docArgs);
//	}
}
