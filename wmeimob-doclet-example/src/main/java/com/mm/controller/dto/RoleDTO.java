package com.mm.controller.dto;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色DTO
 * @author zJun
 * @date 2018年6月28日 上午10:43:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
	
	/**
	 * 主键ID
	 */
	private BigInteger id;

	/**
	 * 角色名称
	 */
	private String roleName;
}
