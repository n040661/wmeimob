package com.mm.controller.dto;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录用户
 * @author zJun
 * @date 2018年6月27日 下午2:33:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

	/**
	 * 主键ID
	 */
	private BigInteger id;
	/**
	 * 用户名
	 */
	private String adminName;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 性别
	 */
	private Sex sex;
	
	/**
	 * 角色
	 */
	private RoleDTO role;
}
