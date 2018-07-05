package com.mm.basic.po;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * 用户项目
 * @author zJun
 * @date 2018年7月3日 下午4:12:19
 */
@Data
@Entity
public class AdminUserProject {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger id;
	private BigInteger adminId;
	private BigInteger projectId;
}
