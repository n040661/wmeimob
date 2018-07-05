package com.mm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mm.IApiConstant;
import com.mm.basic.po.AdminUser;
import com.wmeimob.exception.AdminException;

public class BaseController {

	/**
	 * 从session中获取AdminUser
	 * @return
	 * @author zJun
	 * @date 2018年7月3日 下午4:35:26
	 */
	public AdminUser getAdminUser() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		AdminUser user = (AdminUser) session.getAttribute(IApiConstant.ADMIN_USER_KEY);
		return user;
	}
	
	/**
	 * 检查是否为admin用户
	 * @author zJun
	 * @date 2018年7月3日 下午4:38:18
	 */
	public void checkAuth() {
		AdminUser user = getAdminUser();
		if(user == null || !"admin".equals(user.getAccount())) {
			throw AdminException.getInstance("您无权执行该操作");
		}
	}
	
	/**
	 * 设置adminUser到session
	 * @param user
	 * @author zJun
	 * @date 2018年7月3日 下午5:17:46
	 */
	public void setAdminUser(AdminUser user) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		session.setAttribute(IApiConstant.ADMIN_USER_KEY, user);
	}
}
