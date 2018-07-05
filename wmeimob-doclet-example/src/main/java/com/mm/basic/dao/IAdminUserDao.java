package com.mm.basic.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mm.basic.po.AdminUser;

/**
 * 
 * @author zJun
 * @date 2018年7月3日 下午3:58:34
 */
public interface IAdminUserDao extends JpaRepository<AdminUser, BigInteger> {

	/**
	 * 根据账号密码查询
	 * @param account
	 * @param password
	 * @return
	 * @author zJun
	 * @date 2018年7月3日 下午5:13:55
	 */
	AdminUser findByAccountAndPassword(String account, String password);
	
	/**
	 * 根据账号查询
	 * @param account
	 * @return
	 * @author zJun
	 * @date 2018年7月4日 上午10:26:28
	 */
	AdminUser findByAccount(String account);
}
