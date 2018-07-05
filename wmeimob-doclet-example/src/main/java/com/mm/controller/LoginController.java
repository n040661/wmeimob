package com.mm.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mm.basic.dao.IAdminUserDao;
import com.mm.basic.po.AdminUser;
import com.wmeimob.exception.AdminException;

/**
 * 登录
 * @author zJun
 * @date 2018年7月3日 下午5:10:27
 */
@RestController
@RequestMapping(value="/login")
public class LoginController extends BaseController {

	@Autowired
	private IAdminUserDao iAdminUserDao;
	
	/**
	 * 登录
	 * @param account 账号 admin
	 * @param password 密码 admin
	 * @return 返回假TOKEN（测试）
	 * @author zJun
	 * @date 2018年7月3日 下午5:19:17
	 */
	@PostMapping
	public String login(String account, String password) {
		AdminUser user = iAdminUserDao.findByAccountAndPassword(account, password);
//		user = iAdminUserDao.findByAccount(account);
		if(user == null) {
			throw AdminException.getInstance("账号不存在或密码错误");
		}
		setAdminUser(user);
		return UUID.randomUUID().toString();
	}
}
