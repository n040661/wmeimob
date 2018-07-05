package com.mm.doclet;

import java.io.File;
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

	/**
	 * 开始执行
	 * @param fileName
	 * @author zJun
	 * @date 2018年6月29日 上午9:32:24
	 */
	public static void go(String fileName, String subpackages) {
		File file = new File(fileName);
		recursion(file, subpackages);
	}
	
	/**
	 * 启动程序
	 * @param sourcepath
	 * @param subpackages
	 * @author zJun
	 * @date 2018年6月26日 下午3:33:47
	 */
	private static void exeCMD(String sourcepath, String subpackages) {
		String[] docArgs = new String[] { "-doclet", ControllerDoclet.class.getName(), "-private", "-sourcepath", sourcepath,
				"-subpackages", subpackages};
		com.sun.tools.javadoc.Main.execute(docArgs);
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
	
	public static void recursion(File file, String subpackages) {
		File[] files = file.listFiles();
		if(files == null) {
			return;
		}
		for (File f : files) {
			if(f.getName().startsWith("\\.")) {
				continue;
			}
			if("src".equals(f.getName())) {
				continue;
			}
			
			if ("pom.xml".equals(f.getName())) {
				String src = f.getParent() + "/src/main/java/";
				if (new File(src).exists()) {
					LogUtil.info(log, "开始加载目录:{}", src);
					exeCMD(src, subpackages);
				}
				continue;
			}
			
			if (file.isDirectory()) {
				recursion(f, subpackages);
			}
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
