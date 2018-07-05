package com.mm.controller.sys;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mm.basic.dao.IProjectDao;
import com.mm.basic.po.Project;
import com.mm.controller.BaseController;

/**
 * 项目管理
 * @author zJun
 * @date 2018年7月3日 下午4:56:12
 */
@RestController
@RequestMapping(value="/sys/project")
public class ProjectController extends BaseController {
	
	@Autowired
	private IProjectDao iProjectDao;

	/**
	 * 获取所有项目
	 * @return
	 * @author zJun
	 * @date 2018年7月3日 下午5:09:14
	 */
	@GetMapping(value="list")
	public List<Project> list() {
		checkAuth();
		List<Project> list = iProjectDao.findAll();
		return list;
	}

	/**
	 * 根据ID获取项目
	 * @param id
	 * @return
	 * @author zJun
	 * @date 2018年7月3日 下午5:09:23
	 */
	@GetMapping
	public Project get(BigInteger id) {
		checkAuth();
		return iProjectDao.findOne(id);
	}
}
