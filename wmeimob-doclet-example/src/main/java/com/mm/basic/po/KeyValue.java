package com.mm.basic.po;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * 环境键值对
 * @author zJun
 * @date 2018年7月3日 下午4:12:30
 */
@Data
@Entity
public class KeyValue {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger id;
	private BigInteger envId;
	private String key;
	private String value;
}
