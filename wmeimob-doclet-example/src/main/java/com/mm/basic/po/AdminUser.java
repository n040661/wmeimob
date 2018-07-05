package com.mm.basic.po;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * 系统用户
 * @author zJun
 * @date 2018年7月3日 下午3:48:14
 */
@Entity
@Data
public class AdminUser {
	/** 主键ID */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger id;
	/** 真实姓名 */
	private String fullName;
	/** 登录账号 */
	private String account;
	/** 密码 */
	private String password;
	/** 创建时间 */
	private Date gmtCreate;
	/** 修改时间 */
	private Date gmtModified;
}
