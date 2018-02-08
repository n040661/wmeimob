package com.wmeimob.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * 跨域设置
 * @author zJun
 * @date 2018年2月5日 上午1:43:01
 */
@Slf4j
public class CorsUtil {
	
	private Map<String, Boolean> map = new HashMap<>();
	
	public CorsUtil(Map<String, Boolean> map) {
		this.map = map;
	}
	
	/**
	 * 设置跨域访问header
	 * @author zJun
	 * @date 2018年2月5日 上午1:56:46
	 */
	public void setCors(HttpServletRequest request, HttpServletResponse response) {
		Boolean flag = map.get(request.getLocalName());
		LogUtil.debug(log, "{}---{}", request.getLocalName());
		if(flag != null && flag) {
			response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
			LogUtil.debug(log, "设置允许跨域{}", request.getHeader("Origin"));
		}
		setHeader(response);
	}
	
	/**
	 * 设置跨域访问header
	 * @author zJun
	 * @date 2018年2月5日 上午1:56:46
	 */
	public void setCors(HttpServletResponse response, String origin) {
		response.setHeader("Access-Control-Allow-Origin", origin);
		setHeader(response);
	}
	
	/**
	 * 设置跨域访问header 通用部分
	 * @author zJun
	 * @date 2018年2月5日 上午1:56:46
	 */
	private void setHeader(HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Methods", "OPTIONS, GET, POST");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		response.setHeader("Access-Control-Max-Age", "86400");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
		response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With");
	}
}
