package com.mm.controller.dto;

import lombok.Data;

/**
 * 汽车DTO
 * @author zJun
 * @date 2018年6月27日 下午2:15:09
 */
@Data
public class CarDTO {

	/**
	 * 颜色
	 * @default 蓝色
	 */
	private String color;
	/**
	 * 类型
	 * @default SUV
	 */
	private String type;
	/**
	 * 品牌
	 * @default 路虎
	 */
	private String brand;
}
