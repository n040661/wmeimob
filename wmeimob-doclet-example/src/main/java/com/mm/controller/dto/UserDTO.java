package com.mm.controller.dto;

import java.math.BigInteger;
import java.util.List;

import lombok.Data;

/**
 * 用户DTO
 * @author zJun
 * @date 2018年6月27日 下午2:12:50
 */
@Data
public class UserDTO {

	/**
	 * 主键ID
	 */
	private BigInteger id;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 年龄
	 * @default 18
	 */
	private String age;
	/**
	 * 性别
	 * @default SIR
	 */
	private Sex sex;
	
	/**
	 * 拥有的汽车
	 */
	private List<CarDTO> cars;
	
	/**
	 * 职业
	 */
	private JobDTO job;
}
