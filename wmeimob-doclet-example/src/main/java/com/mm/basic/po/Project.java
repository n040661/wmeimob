package com.mm.basic.po;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * 项目
 * @author zJun
 * @date 2018年7月3日 下午3:50:43
 */
@Data
@Entity
public class Project {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger id;
	private String projectName;
	private String createFullName;
	private String jsonData;
	private Date gmtCreate;
	private Date gmtModified;
}
