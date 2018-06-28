package com.mm.controller.dto;

import lombok.Data;

/**
 * 工作
 * @author zJun
 * @date 2018年6月27日 下午2:18:27
 */
@Data
public class JobDTO {
	
	/**
	 * 工龄
	 * @default 三年
	 */
	private String workingYears;

	/**
	 * 职业名称
	 * @default 程序员
	 */
	private String jobName;
}
