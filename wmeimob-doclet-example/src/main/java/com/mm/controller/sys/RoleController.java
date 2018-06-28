package com.mm.controller.sys;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mm.controller.dto.PageInfo;
import com.mm.controller.dto.RoleDTO;

/**
 * @header appId 应用ID
 * @author zJun
 * @date 2018年6月28日 上午10:46:16
 */
@RestController
@RequestMapping(name = "/sys/role", headers = { "appId" })
public class RoleController {

	/**
	 * 分页查询角色列表
	 * @return 返回说明
	 * @author zJun
	 * @date 2018年6月28日 上午10:56:34
	 */
	@GetMapping
	public PageInfo<RoleDTO> get() {
		List<RoleDTO> list = new ArrayList<>();
		list.add(new RoleDTO(new BigInteger("1001"), "超级管理员1"));
		list.add(new RoleDTO(new BigInteger("1002"), "超级管理员2"));
		list.add(new RoleDTO(new BigInteger("1003"), "超级管理员3"));
		return new PageInfo<>(list);
	}
	
	/**
	 * 新增角色
	 * @header caseId 案例ID
	 * @param dto N/A 角色DTO true RoleDTO
	 * @return 返回说明
	 * @author zJun
	 * @date 2018年6月28日 上午10:56:44
	 */
	@PostMapping(name = "add", headers="caseId")
	public void add(RoleDTO dto) {
	
	}
	
}
