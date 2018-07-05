package com.mm.controller.test;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mm.controller.dto.CarDTO;
import com.mm.controller.dto.UserDTO;

/**
 * 首页接口
 * @author zJun
 * @date 2018年6月27日 下午2:07:46
 */
@RestController
@RequestMapping(value="/test", headers= {"appId"})
public class IndexController {

	/**
	 * 获取首页数据
	 * @see http://www.wmeimob.com
	 * @see http://www.weimob.com
	 * @return 首页数据
	 * @author zJun
	 * @date 2018年6月27日 下午2:09:41
	 */
	@RequestMapping(value="index", method= RequestMethod.GET, headers= {"a", "b", "c=1"})
	@ResponseBody
	public String index() {
		return "index";
	}
	
	/**
	 * 添加用户
	 * @header caseId 案例ID
	 * @param dto 用户DTO
	 * @return 返回说明
	 * @author zJun
	 * @date 2018年6月27日 下午2:21:21
	 */
	@PostMapping(value="addUser", headers="caseId")
	public List<CarDTO> addUser(@RequestBody UserDTO dto) {
		return dto.getCars();
	}
}
