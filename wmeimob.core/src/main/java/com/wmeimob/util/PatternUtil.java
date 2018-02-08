package com.wmeimob.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;


/**
 * 正则表达式
 * @author zJun
 * @date 2018年1月31日 下午3:05:53
 */
@Slf4j
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
		Matcher matcher = p.matcher(url);
		matcher.find();
		try {
			return matcher.group();
		} catch (IllegalStateException e) {
			LogUtil.error(log, "正则表达式截取URL域名错误！URL:{}", url);
			return null;
		}
	}
}
