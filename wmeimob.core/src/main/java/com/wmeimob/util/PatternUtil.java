package com.wmeimob.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 正则表达式
 * @author zJun
 * @date 2018年1月31日 下午3:05:53
 */
public class PatternUtil {

	/**
	 * 正则表达式截取URL中域名
	 * @param url
	 * @return 返回URL中的域名，URL不正确时返回 null
	 * @author zJun
	 * @date 2018年1月31日 下午3:15:18
	 */
	public static String giveDomain(String url) {
		Pattern p = Pattern.compile("[^//]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(url);
		if (m.find()) {
			return m.group();
		} else {
			return null;
		}
	}
	
	/**
	 * 提取双引号中间字符串
	 * @param str
	 * @return
	 * @author zJun
	 * @date 2018年6月26日 下午6:49:43
	 */
	public static String giveQuotationMark(String str) {
		Pattern p = Pattern.compile("(?<=\")(.+?)(?=\")");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return m.group();
		} else {
			return null;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(giveQuotationMark("@org.springframework.web.bind.annotation.RequestMapping(\"/admin/apply\")"));
	}
}
