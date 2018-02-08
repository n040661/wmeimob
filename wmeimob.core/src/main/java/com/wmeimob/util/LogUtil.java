package com.wmeimob.util;

import org.slf4j.Logger;

/**
 * 通用模块日志工具类
 * @author zJun
 * @date 2016年5月19日 下午3:11:58
 */
public class LogUtil {
	
	private final static String begin = "#---------------";
	private final static String end 	= "---------------#";
	
	/**
	 * 输出info日志
	 * @author zJun
	 * @date 2016年5月10日 上午1:56:40
	 */
	public static void info(Logger log, String msg) {
		if(log.isInfoEnabled()) {
			log.info(begin+msg+end);
		}
	}
	
	/**
	 * 输出info日志
	 * @param format占位符{}
	 * @author zJun
	 * @date 2016年5月10日 上午1:56:40
	 */
	public static void info(Logger log, String format, Object ...msg) {
		if(log.isInfoEnabled()) {
			log.info(begin+format+end, msg);
		}
	}
	
	/**
	 * 输出info日志
	 * @author zJun
	 * @date 2017年9月4日 下午5:22:04
	 */
	public static void info(Logger log, String msg, Throwable t) {
		if(log.isInfoEnabled()) {
			log.info(begin+msg+end, t);
		}
	}
	
	/**
	 * 输出debug日志
	 * @author zJun
	 * @date 2016年5月10日 上午1:56:40
	 */
	public static void debug(Logger log, String msg) {
		if(log.isDebugEnabled()) {
			log.debug(begin+msg+end);
		}
	}
	
	/**
	 * 输出debug日志
	 * @param format占位符{}
	 * @author zJun
	 * @date 2016年5月10日 上午1:56:40
	 */
	public static void debug(Logger log, String format, Object ...msg) {
		if(log.isDebugEnabled()) {
			log.debug(begin+format+end, msg);
		}
	}
	
	/**
	 * 输出debug日志
	 * @author zJun
	 * @date 2017年9月4日 下午5:22:04
	 */
	public static void debug(Logger log, String msg, Throwable t) {
		if(log.isDebugEnabled()) {
			log.debug(begin+msg+end, t);
		}
	}
	
	/**
	 * 输出error日志
	 * @author zJun
	 * @date 2016年5月10日 上午1:56:40
	 */
	public static void error(Logger log, String msg) {
		if(log.isErrorEnabled()) {
			log.error(begin+msg+end);
		}
	}
	
	/**
	 * 输出error日志
	 * @param format占位符{}
	 * @author zJun
	 * @date 2016年5月10日 上午1:56:40
	 */
	public static void error(Logger log, String format, Object ...msg) {
		if(log.isErrorEnabled()) {
			log.error(begin+format+end, msg);
		}
	}
	
	/**
	 * 输出error日志
	 * @author zJun
	 * @date 2017年9月4日 下午5:22:04
	 */
	public static void error(Logger log, String msg, Throwable t) {
		if(log.isErrorEnabled()) {
			log.error(begin+msg+end, t);
		}
	}
	
	/**
	 * 输出warn日志
	 * @author zJun
	 * @date 2016年5月10日 上午1:56:40
	 */
	public static void warn(Logger log, String msg) {
		if(log.isWarnEnabled()) {
			log.warn(begin+msg+end);
		}
	}
	
	/**
	 * 输出warn日志
	 * @param format占位符{}
	 * @author zJun
	 * @date 2016年5月10日 上午1:56:40
	 */
	public static void warn(Logger log, String format, Object ...msg) {
		if(log.isWarnEnabled()) {
			log.warn(begin+format+end, msg);
		}
	}
	
	/**
	 * 输出warn日志
	 * @author zJun
	 * @date 2017年9月4日 下午5:22:04
	 */
	public static void warn(Logger log, String msg, Throwable t) {
		if(log.isWarnEnabled()) {
			log.warn(begin+msg+end, t);
		}
	}
	
}
