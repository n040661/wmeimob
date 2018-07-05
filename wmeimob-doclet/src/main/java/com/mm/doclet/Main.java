package com.mm.doclet;

import java.io.File;

public class Main {

	/**
	 * 开始执行
	 * @param fileName
	 * @author zJun
	 * @date 2018年6月29日 上午9:32:24
	 */
	public static void go(String fileName, String subpackages) {
		File file = new File(fileName);
		ControllerDoclet.recursion(file, subpackages);
	}
}
