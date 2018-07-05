package com.mm.basic.po;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * 环境
 * @author zJun
 * @date 2018年7月3日 下午4:09:06
 */
@Entity
@Data
public class Env {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger id;
	private String name;
}
