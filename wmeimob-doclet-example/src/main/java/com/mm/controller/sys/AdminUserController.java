package com.mm.controller.sys;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mm.basic.dao.IAdminUserDao;
import com.mm.basic.po.AdminUser;
import com.mm.controller.BaseController;

/**
 * 系统用户
 * @author zJun
 * @date 2018年7月3日 下午4:18:52
 */
@RestController
@RequestMapping(value="/sys/admin")
public class AdminUserController extends BaseController {
	
	@Autowired
	private IAdminUserDao iAdminUserDao;
	
	/**
	 * 获取所有用户
	 * @return
	 * @author zJun
	 * @date 2018年7月3日 下午4:29:09
	 */
	@GetMapping(value="list")
	public List<AdminUser> list() {
		checkAuth();
		List<AdminUser> list = iAdminUserDao.findAll();
		return list;
	}

	/**
	 * 根据主键ID查询系统用户
	 * @param id 主键ID
	 * @return 返回系统用户信息
	 * @author zJun
	 * @date 2018年7月3日 下午4:39:21
	 */
	@GetMapping
	public AdminUser get(BigInteger id) {
		checkAuth();
		return iAdminUserDao.findOne(id);
	}
	
	/**
	 * 新增系统用户
	 * @param user
	 * @choice fullName account password
	 * @return 返回新增后系统用户ID
	 * @author zJun
	 * @date 2018年7月3日 下午4:44:16
	 */
	@PostMapping
	public BigInteger add(AdminUser user) {
		checkAuth();
		user.setGmtCreate(new Date());
		user.setGmtModified(new Date());
		iAdminUserDao.save(user);
		return user.getId(); 
	}
	
	/**
	 * 修改系统用户
	 * @param user
	 * @author zJun
	 * @date 2018年7月3日 下午4:46:49
	 */
	@PutMapping
	public void put(AdminUser user) {
		checkAuth();
		if(user.getId() == null) {
			throw new RuntimeException("id不能为空");
		}
		user.setGmtCreate(new Date());
		user.setGmtModified(new Date());
		iAdminUserDao.save(user);
	}
	
	/**
	 * 删除系统用户
	 * @param id 主键ID
	 * @author zJun
	 * @date 2018年7月3日 下午4:47:29
	 */
	@DeleteMapping
	public void del(BigInteger id) {
		iAdminUserDao.delete(id);
	}
	
}
