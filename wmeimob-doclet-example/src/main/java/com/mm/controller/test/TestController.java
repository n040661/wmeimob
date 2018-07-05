package com.mm.controller.test;

import java.math.BigInteger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mm.controller.dto.Admin;
import com.mm.controller.dto.RoleDTO;
import com.mm.controller.dto.Sex;
import com.mm.controller.dto.UserDTO;

/**
 * 测试doclet
 * @author zJun
 * @date 2018年7月3日 下午4:42:07
 */
@RestController
@RequestMapping(value="/test")
public class TestController {

	/**
	 * 查询Admin
	 * @param id N/A 主键 true
	 * @return 返回说明
	 * @author zJun
	 * @date 2018年6月27日 下午2:56:14
	 */
	@GetMapping("query")
	public Admin query(BigInteger id) {
		return new Admin(id, "admin", "123123", Sex.LADY, new RoleDTO(new BigInteger("100"), "超级管理员"));
	}
	
	/**
	 * 根据性别查询
	 * @param sex Sex 性别 true
	 * @return 返回说明
	 * @author zJun
	 * @date 2018年6月27日 下午3:50:35
	 */
	@GetMapping("queryBySex")
	public Admin queryBySex(Sex sex) {
		return new Admin(new BigInteger("1"), "admin", "123123", Sex.LADY, new RoleDTO(new BigInteger("100"), "超级管理员"));
	}
	
	/**
	 * 查询UserDTO
	 * @param dto 测试
	 * @exclude cars job
	 * @return UserDTO
	 * @author zJun
	 * @date 2018年7月4日 下午9:25:11
	 */
	@GetMapping("select")
	public UserDTO select(UserDTO dto) {
		return new UserDTO();
	}
}
